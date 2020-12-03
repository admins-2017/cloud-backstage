package com.giantlizardcloud.merchant.service;

import com.giantlizardcloud.merchant.entity.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-12-02
 */
public interface IStatisticsService extends IService<Statistics> {

    /**
     * 根据月份返回前六月统计信息
     * @return
     */
    public List<Statistics> getStatisticsByMonth(Integer year,Integer month);

    /**
     * 添加当月统计详情
     */
    public void insertStatistics();
}
