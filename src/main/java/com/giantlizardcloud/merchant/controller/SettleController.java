package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.AddSettleDto;
import com.giantlizardcloud.merchant.dto.QuerySettleDto;
import com.giantlizardcloud.merchant.service.ISettleService;
import com.giantlizardcloud.merchant.vo.SettleWithAnnexVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/settle")
public class SettleController {

    private final ISettleService settleService;

    @PostMapping
    public JSONResult addSettle(@RequestBody AddSettleDto dto){
        settleService.addSettle(dto);
        return JSONResult.ok();
    }

    @PutMapping("/{id}")
    public JSONResult invalidSettle(@PathVariable("id") Long sid){
        settleService.invalidSettle(sid);
        return JSONResult.ok();
    }

    @GetMapping
    public JSONResult getSettle(@RequestBody QuerySettleDto dto){
        IPage<SettleWithAnnexVo> vos = settleService.getSettleByCondition(dto);
        return JSONResult.ok(vos);
    }

    public SettleController(ISettleService settleService) {
        this.settleService = settleService;
    }
}
