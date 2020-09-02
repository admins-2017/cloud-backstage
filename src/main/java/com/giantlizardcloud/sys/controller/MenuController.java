package com.giantlizardcloud.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import com.giantlizardcloud.config.utils.ParseMenuTreeUtil;
import com.giantlizardcloud.sys.entity.Menu;
import com.giantlizardcloud.sys.entity.RoleMenu;
import com.giantlizardcloud.sys.service.IMenuService;
import com.giantlizardcloud.sys.service.IRoleMenuService;
import com.giantlizardcloud.sys.service.IUserService;
import com.giantlizardcloud.vo.MenuTreeVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@RestController
@RequestMapping("/menu")
@Api(value = "权限管理controller",tags = "权限对应操作")
@Slf4j
public class MenuController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IMenuService menuService;

    /**
     * 根据权限动态加载功能栏
     */
    @GetMapping("/tree")
    public JSONResult generateFunctionBarWithMenu(){
            List<MenuTreeVo> menus = userService.selectMenuTreeByUserId(SecurityUntil.getUserId());
            List<MenuTreeVo> menuList = ParseMenuTreeUtil.parseMenuTree(menus);
            return JSONResult.ok(menuList);
    }


    /**
     * 根据权限动态加载功能栏
     */
    @GetMapping("/basisTree")
    public JSONResult getBasisMenuByUser(){
        List<MenuTreeVo> menus = userService.selectBasisMenuTreeByUserId(SecurityUntil.getUserId());
        log.info(menus.toString());
        List<MenuTreeVo> menuList = ParseMenuTreeUtil.parseMenuTree(menus);
        log.info(menuList.toString());
        return JSONResult.ok(menuList);
    }

    /**
     * 根据权限动态加载功能栏
     */
    @GetMapping("/all")
    public JSONResult getAllMenu(){
        List<MenuTreeVo> menus = userService.selectAllMenu();
        log.info(menus.toString());
        List<MenuTreeVo> menuList = ParseMenuTreeUtil.parseMenuTree(menus);
        return JSONResult.ok(menuList);
    }


    @GetMapping("/{page}/{size}")
    public JSONResult getMenuByPage(@PathVariable Integer page,@PathVariable Integer size){
        Page<Menu> menuPage = new Page<>(page, size);
        IPage<Menu> menus = menuService.page(menuPage, new QueryWrapper<Menu>());
        return JSONResult.ok(menus);
    }

    /**
     * 根据权限动态加载功能栏
     */
    @GetMapping("/role/{id}")
    public JSONResult getBasisMenuByUser(@PathVariable Long id){
        List<Long> list = roleMenuService.getRoleMenus(id);
        return JSONResult.ok(list);
    }
}
