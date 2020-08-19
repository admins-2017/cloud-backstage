package com.giantlizardcloud.sys.controller;


import com.giantlizardcloud.config.json.JSONResult;
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
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/sysRecord")
public class LoginRecordController {

    @GetMapping("/page/size")
    public JSONResult getRecordByPage(@PathVariable Integer page,@PathVariable Integer size){

        return JSONResult.ok();
    }

}
