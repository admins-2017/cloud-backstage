package com.giantlizardcloud.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.quartz.config.QuartzFactory;
import com.giantlizardcloud.sys.entity.ScheduleJob;
import com.giantlizardcloud.sys.mapper.ScheduleJobMapper;
import com.giantlizardcloud.sys.service.IScheduleJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements IScheduleJobService {

    /**
     * 调度器
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * ScheduleJob service
     */
    @Autowired
    private IScheduleJobService jobService;

    @Override
    public void timingTask() {
        //查询数据库是否存在需要定时的任务
        List<ScheduleJob> scheduleJobs = jobService.list(new QueryWrapper<ScheduleJob>().eq("status",0)
                .eq("delete_flag",0));
        if (scheduleJobs != null) {
            scheduleJobs.forEach(this::addJob);
        }
    }

    @Override
    public void addJob(ScheduleJob job) {
        try {
            //创建触发器
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .startNow()
                    .build();

            //创建任务
            JobDetail jobDetail = JobBuilder.newJob(QuartzFactory.class)
                    .withIdentity(job.getJobName())
                    .build();

            //传入调度的数据，在QuartzFactory中需要使用
            jobDetail.getJobDataMap().put("scheduleJob", job);

            //调度作业
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void operateJob(Integer jobStatus, ScheduleJob job) throws SchedulerException {
        JobKey jobKey = new JobKey(job.getJobName());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (null==jobDetail){
            log.error("任务为空");
        }
        switch (jobStatus){
            case 1:
                scheduler.resumeJob(jobKey);
                break;
            case 2:
                scheduler.pauseJob(jobKey);
                break;
            case 3:
                scheduler.deleteJob(jobKey);
                break;
        }
    }


    @Override
    public void startAllJob() throws SchedulerException {
        scheduler.start();
    }

    @Override
    public void pauseAllJob() throws SchedulerException {
        scheduler.standby();
    }
}
