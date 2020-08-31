package com.giantlizardcloud.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.dto.RoleDto;
import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.service.IRoleService;
import com.giantlizardcloud.vo.RoleWithUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public JSONResult getAll(){
        List<Role> roleList = roleService.list(new QueryWrapper<Role>().eq("del_flag", "0"));
        return JSONResult.ok(roleList);
    }

    @GetMapping("/{page}/{size}")
    public JSONResult getAllByPage(@PathVariable Integer page,@PathVariable Integer size){
        Page<Role> rolePage = new Page<>(page, size);
        IPage<Role> list = roleService.page(rolePage, new QueryWrapper<Role>().eq("del_flag", "0"));
        return JSONResult.ok(list);
    }

    @GetMapping("/{roleId}")
    public JSONResult getUserByRoleId(@PathVariable Long roleId){
        log.info(roleId.toString());
        List<RoleWithUserVo> list=roleService.getUserByRoleId(roleId);
        return JSONResult.ok(list);
    }

    @PostMapping
    public JSONResult addRole(RoleDto roleDto){
        log.info(roleDto.toString());
        roleService.insertRoleWithMenu(roleDto);
        return JSONResult.ok("新增角色成功");
    }

    @PutMapping
    public JSONResult updateRole(RoleDto roleDto){
        log.info(roleDto.toString());
        roleService.updateRole(roleDto);
        return JSONResult.ok("修改角色成功");
    }

    @DeleteMapping("{roleId}")
    public JSONResult delRole(@PathVariable Long roleId){
        log.info(roleId.toString());
        roleService.updateRoleStatus(roleId);
        return JSONResult.ok("删除角色成功");
    }

    @PutMapping("/default/{roleId}")
    public JSONResult updateDefaultRole(@PathVariable Long roleId){
        roleService.updateDefaultRole(roleId);
        return JSONResult.ok("已修改默认角色");
    }

    @GetMapping("/like/{name}/{page}/{size}")
    public JSONResult getLikeRole(@PathVariable String name,@PathVariable Integer page,@PathVariable Integer size){
        Page<Role> rolePage = new Page<>(page, size);
        IPage<Role> list = roleService.page(rolePage, new QueryWrapper<Role>().like("role_name",name)
                .eq("del_flag", "0"));
        return JSONResult.ok(list);
    }
}
