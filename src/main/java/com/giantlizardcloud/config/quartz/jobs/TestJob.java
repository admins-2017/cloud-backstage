package com.giantlizardcloud.config.quartz.jobs;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Demo class
 *
 * @author 康东伟
 * @date 2019/05/14
 */
@Component("testJob")
@Transactional(rollbackFor = Exception.class)
public class TestJob {

    /**
     * 默认执行方法
     */
    public void execute(){
        System.out.println("time:"+LocalDateTime.now());
        System.out.println("执行了默认的方法");
    }

    /**
     * 默认执行方法
     */
    public void test(){
        System.out.println("time:"+LocalDateTime.now());
        System.out.println("执行了test的方法");
    }
 }
