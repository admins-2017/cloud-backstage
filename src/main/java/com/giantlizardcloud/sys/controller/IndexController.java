package com.giantlizardcloud.sys.controller;

import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.merchant.enums.IndexKeyEnum;
import com.giantlizardcloud.vo.IndexDataVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * @author kang
 */

@RequestMapping("index")
@RestController
@Api(value = "权限管理",tags = "权限对应操作")
@Slf4j
public class IndexController {

    @Autowired
    private RedisOperator operator;

    @GetMapping
    public JSONResult index(){
        LocalDate now = LocalDate.now();
        Set<ZSetOperations.TypedTuple<String>> testZSet = operator.reverseRankZset(IndexKeyEnum.SALE.getMessage(), 0, -1);
        Map<Object, Object> hgetall = operator.hgetall(now.getYear() + "-" + now.getMonthValue());
        IndexDataVo indexDataVo = new IndexDataVo();
        indexDataVo.setCommodityList(testZSet);
        indexDataVo.setNumber(hgetall);
        return JSONResult.ok(indexDataVo);
    }
}
