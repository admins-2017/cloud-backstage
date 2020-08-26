package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.merchant.service.IShopService;
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
public class ShopController {

    @Autowired
    private IShopService shopService;

    @GetMapping
    public JSONResult getAllShop(){
        List<Shop> shops = shopService.list(new QueryWrapper<Shop>().lt("shop_status", 3));
        return JSONResult.ok(shops);
    }

    @GetMapping("/{name}")
    public JSONResult getShopLikeName(@PathVariable String name){
        List<Shop> list = shopService.list(new QueryWrapper<Shop>().likeRight("shop_name", name));
        return JSONResult.ok(list);
    }
}
