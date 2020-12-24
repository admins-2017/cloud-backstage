package com.giantlizardcloud.sys.controller;


import com.giantlizardcloud.config.aspect.SysOperationLog;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.dto.RecordDto;
import com.giantlizardcloud.sys.entity.OperationRecord;
import com.giantlizardcloud.sys.service.IOperationRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "用户操作记录",tags = "操作记录对应操作")
@Slf4j
public class OperationRecordController {

    private final IOperationRecordService recordService;

    public OperationRecordController(IOperationRecordService recordService) {
        this.recordService = recordService;
    }

//    @ApiOperation(value="分页获取操作记录",notes = "页码和条数")
//    @GetMapping("/{page}/{size}")
//    public JSONResult getRecordByPage(@PathVariable Integer page , @PathVariable Integer size){
//        Page<OperationRecord> recordPage = new Page<>(page, size);
//        Page<OperationRecord> records = recordService.page(recordPage);
//        return JSONResult.ok(records);
//    }

    @GetMapping
    @SysOperationLog(description = "查询操作记录")
    @ApiOperation(value="根据查询条件获取",notes = "记录dto")
    public JSONResult getRecordByCondition(RecordDto dto){
        return JSONResult.ok(recordService.getRecordByCondition(dto));
    }
}
