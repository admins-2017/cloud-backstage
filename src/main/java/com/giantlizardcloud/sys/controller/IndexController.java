package com.giantlizardcloud.sys.controller;

import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.merchant.entity.Statistics;
import com.giantlizardcloud.merchant.enums.IndexKeyEnum;
import com.giantlizardcloud.merchant.service.IDayService;
import com.giantlizardcloud.merchant.service.IStatisticsService;
import com.giantlizardcloud.vo.IndexDataVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.*;

/**
 * @author kang
 */

@RequestMapping(value = "index")
@RestController
@Api(value = "首页统计信息",tags = "首页统计")
@Slf4j
public class IndexController {

    private final RedisOperator operator;
    private final IStatisticsService statisticsService;
    private final IDayService dayService;

    @GetMapping
    public JSONResult index(){
        LocalDate now = LocalDate.now();
        Set<ZSetOperations.TypedTuple<String>> rank = operator.reverseRankZset(
                IndexKeyEnum.SALE.getMessage()+":"+now.getYear() + "-" + now.getMonthValue(), 0, -1);
        Map<Object, Object> map = operator.hgetall(IndexKeyEnum.STATISTICS.getMessage()+":"+now.getYear() + "-" + now.getMonthValue());
        IndexDataVo indexDataVo = new IndexDataVo();
        indexDataVo.setCommodityList(rank);
        indexDataVo.setNumber(map);
        List<Statistics> statistics = statisticsService.getStatisticsByMonth(now.getYear(), now.getMonthValue());
//        将list倒序
        reverse(statistics);
        indexDataVo.setStatistics(statistics);
        indexDataVo.setLastWeekCounts(dayService.getLastWeekCountByDay());
        return JSONResult.ok(indexDataVo);
    }

    public IndexController(RedisOperator operator,IStatisticsService statisticsService ,IDayService dayService) {
        this.operator = operator;
        this.statisticsService = statisticsService;
        this.dayService = dayService;
    }
}
