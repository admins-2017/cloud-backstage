package com.giantlizardcloud.merchant.controller;


import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.service.ISettleService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    public JSONResult addSettle(){
        return JSONResult.ok();
    }

    public JSONResult invalidSettle(){
        return JSONResult.ok();
    }

    public JSONResult getSettle(){
        return JSONResult.ok();
    }

    public SettleController(ISettleService settleService) {
        this.settleService = settleService;
    }
}
