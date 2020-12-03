package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.merchant.entity.Day;
import com.giantlizardcloud.merchant.entity.Order;
import com.giantlizardcloud.merchant.entity.PurchaseOrder;
import com.giantlizardcloud.merchant.mapper.DayMapper;
import com.giantlizardcloud.merchant.service.IDayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.service.IOrderService;
import com.giantlizardcloud.merchant.service.IPurchaseOrderService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-12-03
 */
@Service
public class DayServiceImpl extends ServiceImpl<DayMapper, Day> implements IDayService {

    private final IOrderService orderService;
    private final IPurchaseOrderService purchaseOrderService;

    @Override
    public void addCountByDay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(now.getYear(),now.getMonthValue() , now.getDayOfMonth(), 23, 59);
        int saleCount = orderService.count(new QueryWrapper<Order>().eq("order_status", 1).between("order_date", start, end));
        int returnSaleCount = orderService.count(new QueryWrapper<Order>().eq("order_status", 2).between("order_date", start, end));
        int purchaseCount = purchaseOrderService.count(new QueryWrapper<PurchaseOrder>().eq("purchase_status", 1).between("purchase_date", start, end));
        int returnPurchaseCount = purchaseOrderService.count(new QueryWrapper<PurchaseOrder>().eq("purchase_status", 2).between("purchase_date", start, end));
        save(new Day(LocalDate.now().getYear(),LocalDate.now().getMonthValue(),LocalDate.now().getDayOfMonth(),saleCount,purchaseCount,returnSaleCount,returnPurchaseCount));
    }

    @Override
    public List<Day> getLastWeekCountByDay() {
        LocalDate now = LocalDate.now();
        // 求这个日期上一周的周一、周日
        LocalDate todayOfLastWeek = now.minusDays(7);
        LocalDate monday = todayOfLastWeek.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1);
        LocalDate sunday = todayOfLastWeek.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1);
        List<Integer> list = new ArrayList<>();
        long distance = ChronoUnit.DAYS.between(monday, sunday);
        Stream.iterate(monday, d -> d.plusDays(1)).limit(distance + 1).
                forEach(f -> list.add(f.getDayOfMonth()));
        return list(new QueryWrapper<Day>().select("day_year,day_month,day_date" +
                ",sale_number,purchase_number,return_sale_number,return_purchase_number")
                .between("day_year",monday.getYear(),sunday.getYear())
                .between("day_month",monday.getMonthValue(),sunday.getMonthValue())
                .in("day_date",list));
    }

    public DayServiceImpl(IOrderService orderService, IPurchaseOrderService purchaseOrderService) {
        this.orderService = orderService;
        this.purchaseOrderService = purchaseOrderService;
    }
}
