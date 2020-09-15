package com.giantlizardcloud.config.quartz.config;

import com.giantlizardcloud.sys.entity.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author kang
 * @date 2019/11/06
 */
@Slf4j
public class QuartzFactory implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //获取调度数据
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        log.info(scheduleJob.toString());

//        获取对应的Bean
        Object object = SpringUtil.getBean(scheduleJob.getBeanName());
        try {
            //利用反射执行对应方法
            if (scheduleJob.getMethodName() .equals("test")){
                Method method = object.getClass().getMethod(scheduleJob.getMethodName(),String.class);
                log.info(method.getName());
                log.info(method.toString());
                String name = "测试";
                    log.info("执行test");
                try {
                    method.invoke(object,name);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else {
                Method method = null;
                try {
                    method = object.getClass().getMethod(scheduleJob.getMethodName());
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                log.info(method.getName());
                log.info(method.toString());
                try {
                    method.invoke(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}