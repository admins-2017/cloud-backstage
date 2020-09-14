package com.giantlizardcloud.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.dto.JobDto;
import com.giantlizardcloud.sys.entity.ScheduleJob;
import com.giantlizardcloud.sys.service.IScheduleJobService;
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
public class ScheduleJobController {

    private IScheduleJobService jobService;

    public ScheduleJobController(IScheduleJobService jobService){
        this.jobService = jobService;
    }

    @PostMapping
    public JSONResult addJob(JobDto dto){
        return JSONResult.ok();
    }

    @PutMapping
    public JSONResult updJob(JobDto dto){
        return JSONResult.ok();
    }

    @DeleteMapping("/{id}")
    public JSONResult delJob(@PathVariable Integer id){
        jobService.update(new UpdateWrapper<ScheduleJob>().set("delete_flag",1).eq("id",id));
        return JSONResult.ok();
    }

    @GetMapping("/{page}/{size}")
    public JSONResult findAllJob(@PathVariable Integer page ,@PathVariable Integer size){
        Page<ScheduleJob> jobPage = new Page<>(page, size);
        IPage<ScheduleJob> jobs = jobService.page(jobPage);
        return JSONResult.ok(jobs);
    }

    @GetMapping
    public JSONResult findJobByCondition(JobDto dto){
        return JSONResult.ok();
    }
}
