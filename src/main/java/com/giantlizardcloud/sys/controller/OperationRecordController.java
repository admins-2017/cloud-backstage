package com.giantlizardcloud.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.sys.entity.OperationRecord;
import com.giantlizardcloud.sys.service.IOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class OperationRecordController {

    @Autowired
    private IOperationRecordService recordService;

    @GetMapping("/{page}/{size}")
    public JSONResult getRecordByPage(@PathVariable Integer page , @PathVariable Integer size){
        Page<OperationRecord> recordPage = new Page<>(page, size);
        Page<OperationRecord> records = recordService.page(recordPage);
        return JSONResult.ok(records);
    }
}
