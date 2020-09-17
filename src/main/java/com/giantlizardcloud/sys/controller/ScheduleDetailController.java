package com.giantlizardcloud.sys.controller;


import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.sys.entity.ScheduleDetail;
import com.giantlizardcloud.sys.service.IScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/scheduleDetail")
public class ScheduleDetailController {

    private final IScheduleDetailService detailService;

    public ScheduleDetailController(IScheduleDetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping
    public JSONResult getAllScheduleDetail(){
        List<ScheduleDetail> list = detailService.list();
        return JSONResult.ok(list);
    }
}
