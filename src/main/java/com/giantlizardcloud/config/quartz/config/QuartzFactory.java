package com.giantlizardcloud.config.quartz.config;

import com.giantlizardcloud.config.json.JsonUtils;
import com.giantlizardcloud.config.quartz.jobs.TestJob;
import com.giantlizardcloud.dto.job.ScheduleJobWithDetail;
import com.giantlizardcloud.sys.entity.ScheduleJob;
import com.giantlizardcloud.sys.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;

/**
 * @author kang
 * @date 2019/11/06
 */
@Slf4j
public class QuartzFactory implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //获取调度数据
        ScheduleJobWithDetail scheduleJob = (ScheduleJobWithDetail) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        //获取对应的Bean
        Object object = SpringUtil.getBean(scheduleJob.getBeanName());
        try {
            //利用反射执行对应方法
            if (scheduleJob.getMethodArgType() != null && !scheduleJob.getMethodArgType().equals("")) {
                if(scheduleJob.getMethodArgType().equals("java.lang.String")){
                    Method method = object.getClass().getMethod(scheduleJob.getMethodName(), Class.forName(scheduleJob.getMethodArgType()));
                    method.invoke(object, scheduleJob.getMethodParams());
                }else if(scheduleJob.getMethodArgType().equals("java.util.List,com.giantlizardcloud.sys.entity.User")){
                    //将字段的类型分割
                    String[] split = scheduleJob.getMethodArgType().split(",");
                    Method method = object.getClass().getMethod(scheduleJob.getMethodName(), Class.forName(split[0]));
                    Object o = JsonUtils.jsonToList(scheduleJob.getMethodParams(), Class.forName(split[1]));
                    method.invoke(object, o);
                }else{
                    Method method = object.getClass().getMethod(scheduleJob.getMethodName(), Class.forName(scheduleJob.getMethodArgType()));
                    Object o = JsonUtils.jsonToPojo(scheduleJob.getMethodParams(), Class.forName(scheduleJob.getMethodArgType()));
                    method.invoke(object, o);
                }
            } else {
                Method method = object.getClass().getMethod(scheduleJob.getMethodName());
                method.invoke(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}