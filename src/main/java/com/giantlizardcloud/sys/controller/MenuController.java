package com.giantlizardcloud.sys.controller;


import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import com.giantlizardcloud.config.utils.ParseMenuTreeUtil;
import com.giantlizardcloud.sys.service.IUserService;
import com.giantlizardcloud.vo.MenuTreeVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<MenuTreeVo> menuList = ParseMenuTreeUtil.parseMenuTree(menus);
        return JSONResult.ok(menuList);
    }
}
