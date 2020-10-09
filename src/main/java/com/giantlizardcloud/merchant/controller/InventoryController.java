package com.giantlizardcloud.merchant.controller;


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
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    /**
     * 查询商铺下所有商品的库存
     * @return 返回json格式的查询结果
     */
    @GetMapping("/{id}")
    public JSONResult getCommodityByShop(@PathVariable("id") Long shopId){
        return JSONResult.ok();
    }
}
