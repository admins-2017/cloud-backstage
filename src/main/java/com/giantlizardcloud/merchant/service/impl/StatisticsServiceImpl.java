package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.merchant.entity.Statistics;
import com.giantlizardcloud.merchant.enums.IndexKeyEnum;
import com.giantlizardcloud.merchant.mapper.StatisticsMapper;
import com.giantlizardcloud.merchant.service.IStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-12-02
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements IStatisticsService {

    @Autowired
    private final RedisOperator operator;

    @Override
    public List<Statistics> getStatisticsByMonth(Integer year,Integer month) {
        return this.baseMapper.selectList(new QueryWrapper<Statistics>().eq("statistics_year",year)
                .lt("statistics_month",month)
                .orderByDesc("statistics_month").last("limit 6"));
    }

    @Override
    public void insertStatistics() {
        LocalDate now = LocalDate.now();
        List<Map.Entry<Object, Object>> collect = operator.hgetall(IndexKeyEnum.STATISTICS.getMessage() + ":" + now.getYear() + "-" + now.getMonthValue())
                .entrySet().stream().filter(e -> !"saleCount".equals(e.getKey()) && !"purchaseCount".equals(e.getKey()))
                .collect(Collectors.toList());
        if (collect.size()>0) {
            this.baseMapper.insert(new Statistics(now.getYear(), now.getMonthValue(),
                    Double.valueOf(((Integer) collect.get(0).getValue()).toString())
                    , Double.valueOf(((Integer) collect.get(1).getValue()).toString())));
        }
    }

    public StatisticsServiceImpl(RedisOperator operator) {
        this.operator = operator;
    }
}
