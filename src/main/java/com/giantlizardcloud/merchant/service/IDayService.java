package com.giantlizardcloud.merchant.service;

import com.giantlizardcloud.merchant.entity.Day;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-12-03
 */
public interface IDayService extends IService<Day> {
    /**
     * 每天 11.59统计今日进货及销售次数
     */
    void addCountByDay();

    /**
     * 根据开始时间及结束时间返回统计结果
     * @return 统计列表
     */
    List<Day> getLastWeekCountByDay();
}
