package com.giantlizardcloud.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.aspect.SysOperationLog;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.dto.RoleDto;
import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.service.IRoleService;
import com.giantlizardcloud.vo.RoleWithUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "角色管理",tags = "角色对应操作")
@Slf4j
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @SysOperationLog(description = "获取所有角色")
    @ApiOperation(value="获取所有角色")
    public JSONResult getAll(){
        List<Role> roleList = roleService.list(new QueryWrapper<Role>().eq("del_flag", "0"));
        return JSONResult.ok(roleList);
    }

    @GetMapping("/{page}/{size}")
    @SysOperationLog(description = "根据分页获取角色")
    @ApiOperation(value="根据分页获取角色",notes = "页码和条数")
    public JSONResult getAllByPage(@PathVariable Integer page,@PathVariable Integer size){
        Page<Role> rolePage = new Page<>(page, size);
        IPage<Role> list = roleService.page(rolePage, new QueryWrapper<Role>().eq("del_flag", "0"));
        return JSONResult.ok(list);
    }

    @GetMapping("/{roleId}")
    @SysOperationLog(description = "根据用户获取角色")
    @ApiOperation(value="根据用户获取角色",notes = "角色id")
    public JSONResult getUserByRoleId(@PathVariable Long roleId){
        log.info(roleId.toString());
        List<RoleWithUserVo> list=roleService.getUserByRoleId(roleId);
        return JSONResult.ok(list);
    }

    @PostMapping
    @SysOperationLog(description = "新增角色")
    @ApiOperation(value="新增角色",notes = "角色dto")
    public JSONResult addRole(RoleDto roleDto){
        log.info(roleDto.toString());
        roleService.insertRoleWithMenu(roleDto);
        return JSONResult.ok("新增角色成功");
    }

    @PutMapping
    @SysOperationLog(description = "修改角色")
    @ApiOperation(value="修改角色",notes = "角色dto")
    public JSONResult updateRole(RoleDto roleDto){
        log.info(roleDto.toString());
        roleService.updateRole(roleDto);
        return JSONResult.ok("修改角色成功");
    }

    @DeleteMapping("{roleId}")
    @SysOperationLog(description = "删除角色")
    @ApiOperation(value="删除角色",notes = "角色id")
    public JSONResult delRole(@PathVariable Long roleId){
        log.info(roleId.toString());
        roleService.updateRoleStatus(roleId);
        return JSONResult.ok("删除角色成功");
    }

    @PutMapping("/default/{roleId}")
    @SysOperationLog(description = "修改默认角色")
    @ApiOperation(value="修改默认角色",notes = "角色id")
    public JSONResult updateDefaultRole(@PathVariable Long roleId){
        roleService.updateDefaultRole(roleId);
        return JSONResult.ok("已修改默认角色");
    }

    @GetMapping("/like/{name}/{page}/{size}")
    @SysOperationLog(description = "根据角色名查询")
    @ApiOperation(value="根据角色名查询",notes = "角色名称，页码，条数")
    public JSONResult getLikeRole(@PathVariable String name,@PathVariable Integer page,@PathVariable Integer size){
        Page<Role> rolePage = new Page<>(page, size);
        IPage<Role> list = roleService.page(rolePage, new QueryWrapper<Role>().like("role_name",name)
                .eq("del_flag", "0"));
        return JSONResult.ok(list);
    }
}
