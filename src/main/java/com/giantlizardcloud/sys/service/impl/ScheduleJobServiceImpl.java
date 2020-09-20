package com.giantlizardcloud.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.quartz.config.QuartzFactory;
import com.giantlizardcloud.dto.job.ScheduleJobWithDetail;
import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.sys.entity.ScheduleJob;
import com.giantlizardcloud.sys.mapper.ScheduleJobMapper;
import com.giantlizardcloud.sys.service.IScheduleJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
@Service
@Slf4j
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
        List<ScheduleJobWithDetail> scheduleJobs =this.baseMapper.getTimingTasks();
        if (scheduleJobs != null) {
            scheduleJobs.forEach(this::addJob);
        }
    }

    @Override
    public void addJob(ScheduleJobWithDetail job) {
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
            log.info("修改后添加完成");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void operateJob(Integer jobStatus, ScheduleJobWithDetail job) throws SchedulerException {
        JobKey jobKey = new JobKey(job.getJobName());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (null==jobDetail){
            log.error("任务为空");
            addJob(job);
        }
        switch (jobStatus){
            case 1:
                scheduler.resumeJob(jobKey); //恢复任务
                break;
            case 2:
                scheduler.pauseJob(jobKey); //暂停任务
                break;
            case 3:
                scheduler.deleteJob(jobKey); //删除任务
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

    /**
     * 获取所有任务列表
     *
     * @return
     */
    @Override
    public List<ScheduleJobWithDetail> getAllJob()throws Exception {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        List<ScheduleJobWithDetail> jobList = new ArrayList<>();
        Set<JobKey> jobKeys = null;
        jobKeys = scheduler.getJobKeys(matcher);
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                ScheduleJobWithDetail jobDto = new ScheduleJobWithDetail();

                jobDto.setJobName(jobKey.getName());
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                jobDto.setBeanName(triggerState.name());

                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    jobDto.setCronExpression(cronExpression);
                }
                jobList.add(jobDto);
            }
        }
        return jobList;
    }

    @Override
    public ScheduleJobWithDetail getTaskId(Integer id) {
        return this.baseMapper.getTaskId(id);
    }

    @Override
    public IPage<ScheduleJobWithDetail> getTaskByPage(Integer page , Integer size) {
        page = (page-1)*size;
        List<ScheduleJobWithDetail> tasks = this.baseMapper.getTaskByPage(page, size);
        IPage<ScheduleJobWithDetail> jobs = new Page<>();
        jobs.setRecords(tasks);
        jobs.setTotal(this.baseMapper.selectCount(new QueryWrapper<>()));
        return jobs;
    }

    @Override
    public IPage<ScheduleJobWithDetail> getTaskByLikeName(Integer page, Integer size, String likeName) {
        page = (page-1)*size;
        List<ScheduleJobWithDetail> tasks = this.baseMapper.getTaskByLikeName(page, size,likeName);
        IPage<ScheduleJobWithDetail> jobs = new Page<>();
        jobs.setRecords(tasks);
        jobs.setTotal(this.baseMapper.selectCount(new QueryWrapper<ScheduleJob>().like("job_name",likeName)));
        return jobs;
    }

}
