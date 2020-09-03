package com.giantlizardcloud.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.dto.RecordDto;
import com.giantlizardcloud.sys.entity.OperationRecord;
import com.giantlizardcloud.sys.service.IOperationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/record")
@Slf4j
public class OperationRecordController {

    @Autowired
    private IOperationRecordService recordService;

    @GetMapping("/{page}/{size}")
    public JSONResult getRecordByPage(@PathVariable Integer page , @PathVariable Integer size){
        Page<OperationRecord> recordPage = new Page<>(page, size);
        Page<OperationRecord> records = recordService.page(recordPage);
        return JSONResult.ok(records);
    }

    @GetMapping
    public JSONResult getRecordByCondition(RecordDto dto){
        log.info(dto.toString());
        List<OperationRecord> list = recordService.list(new QueryWrapper<OperationRecord>()
                .like(!"".equals(dto.getRequestUser()),"request_user",dto.getRequestUser())
                .eq(!"".equals(dto.getRequestType()),"request_type",dto.getRequestType())
                .ge(!"".equals(dto.getStartTime()),"request_time", dto.getStartTime())
                .le(!"".equals(dto.getEndTime()),"request_time", dto.getEndTime())
        );
        return JSONResult.ok(list);
    }
}
