package com.giantlizardcloud.index;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.merchant.dto.AddOrderAndDetailDto;
import com.giantlizardcloud.merchant.dto.AddPurchaseOrderDto;
import com.giantlizardcloud.merchant.entity.*;
import com.giantlizardcloud.merchant.enums.IndexKeyEnum;
import com.giantlizardcloud.merchant.mapper.StatisticsMapper;
import com.giantlizardcloud.merchant.service.ICommodityService;
import com.giantlizardcloud.merchant.service.IDayService;
import com.giantlizardcloud.merchant.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    private IDayService dayService;
    @Autowired
    private IOrderService orderService;

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
    public void testDay(){
        LocalDate now = LocalDate.now();
        // 求这个日期上一周的周一、周日
        LocalDate todayOfLastWeek = now.minusDays(7);
        LocalDate monday = todayOfLastWeek.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1);
        LocalDate sunday = todayOfLastWeek.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1);
        System.out.println("当前日期：" + now + " 上一周的周一：" + monday + " " + monday.getDayOfWeek());
        System.out.println("当前日期：" + now + " 上一周的周日：" + sunday + " " + sunday.getDayOfWeek());
        List<Integer> list = new ArrayList<>();
        long distance = ChronoUnit.DAYS.between(monday, sunday);
        Stream.iterate(monday, d -> d.plusDays(1)).limit(distance + 1).
                forEach(f -> list.add(f.getDayOfMonth()));
        dayService.list(new QueryWrapper<Day>().select("day_year,day_month,day_date" +
                ",sale_number,purchase_number,return_sale_number,return_purchase_number")
                .between("day_year",monday.getYear(),sunday.getYear())
                .between("day_month",monday.getMonthValue(),sunday.getMonthValue())
                .in("day_date",list));
        list.forEach(System.out::println);
    }



    @Test
    public void testInsertDay(){

        for (int i = 0; i <31 ; i++) {
            dayService.save(new Day(2020,11,i,
                    1,1,1,1));
        }
    }

    @Test
    public void testCountByDay(){
        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime start = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
//        LocalDateTime end = LocalDateTime.of(now.getYear(),now.getMonthValue() , now.getDayOfMonth(), 23, 59);
        LocalDateTime start = LocalDateTime.of(2020 , 10,27, 00, 00);
        LocalDateTime end = LocalDateTime.of(2020,10,27 , 23, 59);

        int saleCount = orderService.count(new QueryWrapper<Order>().eq("order_status", 1).between("order_date", start, end));
        int returnSaleCount = orderService.count(new QueryWrapper<Order>().eq("order_status", 2).between("order_date", start, end));
        System.out.println(saleCount);
        System.out.println(returnSaleCount);

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
