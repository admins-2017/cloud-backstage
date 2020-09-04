package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.merchant.service.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-08-20
 */
@RestController
@RequestMapping("/shop")
@Api(value = "商铺管理",tags = "商铺对应操作")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @GetMapping
    @ApiOperation(value="获取所有商铺")
    public JSONResult getAllShop(){
        List<Shop> shops = shopService.list(new QueryWrapper<Shop>().lt("shop_status", 3));
        return JSONResult.ok(shops);
    }

    @GetMapping("/{name}")
    @ApiOperation(value="根据商铺名模糊查询获取商铺",notes = "商铺名")
    public JSONResult getShopLikeName(@PathVariable String name){
        List<Shop> list = shopService.list(new QueryWrapper<Shop>().likeRight("shop_name", name));
        return JSONResult.ok(list);
    }
}
