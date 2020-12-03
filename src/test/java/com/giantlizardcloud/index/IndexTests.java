package com.giantlizardcloud.index;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.merchant.dto.AddOrderAndDetailDto;
import com.giantlizardcloud.merchant.dto.AddPurchaseOrderDto;
import com.giantlizardcloud.merchant.entity.Commodity;
import com.giantlizardcloud.merchant.entity.OrderDetails;
import com.giantlizardcloud.merchant.entity.Statistics;
import com.giantlizardcloud.merchant.enums.IndexKeyEnum;
import com.giantlizardcloud.merchant.mapper.StatisticsMapper;
import com.giantlizardcloud.merchant.service.ICommodityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.reverse;

@SpringBootTest
@Slf4j
public class IndexTests {

    @Autowired
    private RedisOperator operator;
    @Autowired
    private ICommodityService commodityService;
    @Autowired
    private StatisticsMapper mapper;

    @Test
    public void testZSet(){
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"雀巢咖啡",3.0);
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"康师傅冰糖雪梨",4.0);
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"康师傅酸梅汁",2.0);
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"茶π",1.0);
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"阿萨姆奶茶",7.0);
    }

    @Test
    public void testRankZSet(){
        Set<ZSetOperations.TypedTuple<String>> testZSet = operator.reverseRankZset(IndexKeyEnum.SALE.getMessage(), 0, -1);
        testZSet.forEach( a-> {
            System.out.println(a.getValue() + ": " +a.getScore());
        });
    }

    @Test
    public void testAdd(){
        LocalDate now = LocalDate.now();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCommodityId(5);
        orderDetails.setOrderDetailNumber(10);
        Commodity commodity = commodityService.getOne(new QueryWrapper<Commodity>().select("commodity_name").eq("commodity_id", orderDetails.getCommodityId()));
        operator.incrementScore(IndexKeyEnum.SALE.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                ,commodity.getCommodityName(),orderDetails.getOrderDetailNumber());
    }

    @Test
    public void testPurchaseAdd(){
        LocalDate now = LocalDate.now();
        AddPurchaseOrderDto dto = new AddPurchaseOrderDto();
        dto.setPurchaseAmountAfterDiscount(200.0);
        operator.hashIncrBy(IndexKeyEnum.STATISTICS.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                ,IndexKeyEnum.PURCHASE_COUNT.getMessage(),1);
        operator.hIncrByDouble(IndexKeyEnum.STATISTICS.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                ,IndexKeyEnum.AMOUNT_OF_PAYOUT.getMessage(),dto.getPurchaseAmountAfterDiscount());
    }

    @Test
    public void testHash(){
        LocalDate now = LocalDate.now();
        System.out.println();
        System.out.println(now.getMonthValue());
        operator.hset(now.getYear()+"-"+now.getMonthValue(),"purchase",0);
        operator.hset(now.getYear()+"-"+now.getMonthValue(),"sale",0);
        operator.hset(now.getYear()+"-"+now.getMonthValue(),"salesAmount",0);
        operator.hset(now.getYear()+"-"+now.getMonthValue(),"amountOfPayout",0);

    }

    @Test
    public void getAllObject(){
        LocalDate now = LocalDate.now();
        List<Map.Entry<Object, Object>> collect = operator.hgetall(IndexKeyEnum.STATISTICS.getMessage() + ":" + now.getYear() + "-" + now.getMonthValue())
                .entrySet().stream().filter(e -> !"saleCount".equals(e.getKey()) && !"purchaseCount".equals(e.getKey()))
                .collect(Collectors.toList());

        System.out.println();
        System.out.println();

        mapper.insert(new Statistics(now.getYear(),now.getMonthValue(),
                Double.valueOf(((Integer)collect.get(0).getValue()).toString())
                ,Double.valueOf(((Integer)collect.get(1).getValue()).toString())));

    }

    @Test
    public void testMapper(){
        LocalDate now = LocalDate.now();
        List<Statistics> statistics = mapper.selectList(new QueryWrapper<Statistics>().eq("statistics_year",now.getYear())
                .lt("statistics_month",now.getMonthValue())
                .orderByDesc("statistics_month").last("limit 6"));
        reverse(statistics);
        statistics.forEach(System.out::println);
    }

    @Test
    public void testObject(){
        LocalDate now = LocalDate.now();
         operator.hashIncrBy(now.getYear() + "-" + now.getMonthValue(),"purchase",-1);
    }

    @Test
    public void testEnum(){
        LocalDate now = LocalDate.now();
        AddOrderAndDetailDto dto = new AddOrderAndDetailDto();
        dto.setOrderAmountAfterDiscount(200.0);
        operator.hIncrByDouble(IndexKeyEnum.STATISTICS.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                ,IndexKeyEnum.SALE_AMOUNT.getMessage(),-dto.getOrderAmountAfterDiscount());
    }
}
