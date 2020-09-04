package com.giantlizardcloud.sys.controller;


import com.giantlizardcloud.config.json.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "用户登录记录",tags = "登录记录对应操作")
public class LoginRecordController {


    @ApiOperation(value="分页查询获取登录记录", notes="根据分页查询")
    @GetMapping("/page/size")
    public JSONResult getRecordByPage(@PathVariable Integer page,@PathVariable Integer size){
        return JSONResult.ok();
    }

}
