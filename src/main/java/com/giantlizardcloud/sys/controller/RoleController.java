package com.giantlizardcloud.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public JSONResult getAll(){
        List<Role> roleList = roleService.list(new QueryWrapper<Role>().eq("del_flag", "0"));
        return JSONResult.ok(roleList);
    }
}
