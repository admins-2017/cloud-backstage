package com.giantlizardcloud.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.dto.job.ScheduleJobWithDetail;
import com.giantlizardcloud.sys.entity.ScheduleJob;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
public interface IScheduleJobService extends IService<ScheduleJob> {

    /**
     * 服务器启动执行定时任务
     *
     * @author kang
     * @date 2019/1/28 15:38
     */
    void timingTask();

    /**
     * 新增定时任务
     *
     * @author lanjerry
     * @date 2019/1/28 15:44
     * @param job 任务
     */
    void addJob(ScheduleJobWithDetail job);

    /**
     * 操作定时任务
     *
     * @author lanjerry
     * @date 2019/1/28 16:56
     * @param jobStatus 操作枚举
     * @param job 任务
     */
    void operateJob(Integer jobStatus, ScheduleJobWithDetail job) throws SchedulerException;

    /**
     * 启动所有任务
     *
     * @author lanjerry
     * @date 2019/1/28 16:58
     */
    void startAllJob() throws SchedulerException;

    /**
     * 暂停所有任务
     *
     * @author lanjerry
     * @date 2019/1/28 16:58
     */
    void pauseAllJob() throws SchedulerException;

    /**
     * 获取所有任务
     * @return
     * @throws Exception
     */
    List<ScheduleJobWithDetail> getAllJob() throws Exception;

    ScheduleJobWithDetail getTaskId(Integer id);

    IPage<ScheduleJobWithDetail> getTaskByPage(Integer page , Integer size);

    IPage<ScheduleJobWithDetail> getTaskByLikeName(Integer page , Integer size,String likeName);

}
