package com.giantlizardcloud.config.quartz.jobs;


import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.sys.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Demo class
 *
 * @author 康东伟
 * @date 2019/05/14
 */
@Component("testJob")
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TestJob {

    @Autowired
    private RedisOperator operator;

    /**
     * 默认执行方法
     */
    public void execute(){
        log.info("执行了默认的方法");
    }

    /**
     * 默认执行方法
     */
    public void test(User user){
        log.info("执行test："+user.toString());
    }

    /**
     * 默认执行方法
     */
    public void getMailUrl(String url){
        log.info("执行getMailUrl："+url);
    }

    /**
     * 默认执行方法
     */
    public void testList(List<String> list){
        log.info("执行testList");
        list.stream().forEach(System.out::println);
    }

    public void testObjectList(List<User> list){
        log.info("执行testObjectList");
        list.stream().forEach(System.out::println);
    }

    /**
     * 定时统计
     */
    public void timingStatistics(){
        LocalDate now = LocalDate.now();
        Map<Object, Object> hgetall = operator.hgetall(now.getYear() + "-" + now.getMonthValue());
    }
 }
