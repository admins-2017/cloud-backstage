package com.giantlizardcloud.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.dto.JobDto;
import com.giantlizardcloud.dto.job.ScheduleJobWithDetail;
import com.giantlizardcloud.sys.entity.ScheduleJob;
import com.giantlizardcloud.sys.service.IScheduleJobService;
import com.mchange.v2.beans.BeansUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class ScheduleJobController {

    private IScheduleJobService jobService;

    public ScheduleJobController(IScheduleJobService jobService){
        this.jobService = jobService;
    }

    @PostMapping
    public JSONResult addJob(ScheduleJobWithDetail dto){
        log.info(dto.toString());
        ScheduleJob scheduleJob = new ScheduleJob();
        BeanUtils.copyProperties(dto,scheduleJob);
        log.info(scheduleJob.toString());
        jobService.save(scheduleJob);
//        jobService.addJob(scheduleJob);
        return JSONResult.ok();
    }

    @PutMapping
    public JSONResult updJob(JobDto dto){
        return JSONResult.ok();
    }

    @PutMapping("/status")
    public JSONResult updJobStatus(JobDto dto) throws SchedulerException {
        log.info(dto.toString());
        if (dto.getStatus()!=null){
            jobService.update(new UpdateWrapper<ScheduleJob>().set("status", dto.getStatus()).eq("id", dto.getId()));
            ScheduleJobWithDetail job = jobService.getTaskId(dto.getId());
            log.info(job.toString());
            if(dto.getStatus()==1){
                jobService.operateJob(1,job);
                return JSONResult.ok("启动任务成功");
            }else {
                jobService.operateJob(2,job);
                return JSONResult.ok("停止任务成功");
            }
        }else if(dto.getDeleteFlag()!=null){
            jobService.update(new UpdateWrapper<ScheduleJob>().set("delete_flag",dto.getDeleteFlag()).eq("id",dto.getId()));
            if (dto.getDeleteFlag()){
                ScheduleJobWithDetail job = jobService.getTaskId(dto.getId());
                jobService.update(new UpdateWrapper<ScheduleJob>().set("status",2).eq("id",dto.getId()));
                jobService.operateJob(3,job);
                return JSONResult.ok("删除任务成功");
            }else{
                ScheduleJobWithDetail job = jobService.getTaskId(dto.getId());
                jobService.update(new UpdateWrapper<ScheduleJob>().set("status",1).eq("id",dto.getId()));
                jobService.operateJob(1,job);
                return JSONResult.ok("恢复任务成功");
            }
        }
        return JSONResult.ok();
    }


    @GetMapping("/{page}/{size}")
    public JSONResult findAllJob(@PathVariable Integer page ,@PathVariable Integer size){
        IPage<ScheduleJobWithDetail> jobs = jobService.getTaskByPage(page, size);
        return JSONResult.ok(jobs);
    }

    @GetMapping("/{page}/{size}/{likeName}")
    public JSONResult findJobByCondition(@PathVariable Integer page ,@PathVariable Integer size,@PathVariable String likeName){
        IPage<ScheduleJobWithDetail> taskByLikeName = jobService.getTaskByLikeName(page, size, likeName);
        return JSONResult.ok(taskByLikeName);
    }
}
