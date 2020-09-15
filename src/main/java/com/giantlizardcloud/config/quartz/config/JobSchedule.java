package com.giantlizardcloud.config.quartz.config;

import com.giantlizardcloud.sys.service.IScheduleJobService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 服务器启动时开启执行任务配置类
 * @author kang
 */
@Component
public class JobSchedule implements CommandLineRunner {

    @Resource
    private IScheduleJobService jobService;

    /**
     * 任务调度开始
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {
        jobService.timingTask();
    }
}