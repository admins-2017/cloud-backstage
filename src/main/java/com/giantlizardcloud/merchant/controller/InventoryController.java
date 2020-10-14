package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.QueryInventory;
import com.giantlizardcloud.merchant.service.IInventoryService;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;
import com.giantlizardcloud.merchant.vo.InventoryGetCommodityClassVo;
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
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final IInventoryService inventoryService;

    public InventoryController(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/status/{status}")
    public JSONResult getDetailByIndex(@PathVariable Integer status,Long shopId){

        if (status == 1){
            List<InventoryGetCommodityClassVo> vos =  inventoryService.getInventoryCommodity(shopId);
            return JSONResult.ok(vos);
        }else if (status ==2){
            List<CommodityWithShopVo> vos = inventoryService.getZeroInventory(shopId);
            return JSONResult.ok(vos);
        }else if (status == 3) {
            List<CommodityWithShopVo> vos = inventoryService.getWarnInventory(shopId);
            return JSONResult.ok(vos);
        }else {
            List<CommodityWithShopVo> vos = inventoryService.getAmpleInventory(shopId);
            return JSONResult.ok(vos);
        }
    }

    /**
     * 查询商铺下所有商品的库存
     * @return 返回json格式的查询结果
     */
    @GetMapping
    public JSONResult getCommodityByShop(QueryInventory query){
        return JSONResult.ok( inventoryService.getShopUnderCommodity(query));
    }
}
