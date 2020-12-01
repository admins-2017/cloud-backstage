package com.giantlizardcloud.index;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.merchant.dto.AddOrderAndDetailDto;
import com.giantlizardcloud.merchant.dto.AddPurchaseOrderDto;
import com.giantlizardcloud.merchant.entity.Commodity;
import com.giantlizardcloud.merchant.entity.OrderDetails;
import com.giantlizardcloud.merchant.enums.IndexKeyEnum;
import com.giantlizardcloud.merchant.service.ICommodityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@Slf4j
public class IndexTests {

    @Autowired
    private RedisOperator operator;
    @Autowired
    private ICommodityService commodityService;

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
        Map<Object, Object> hgetall = operator.hgetall(now.getYear() + "-" + now.getMonthValue());
        for(Object s:hgetall.keySet()){
                         System.out.println("key : "+s+" value : "+hgetall.get(s));
                   }
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
