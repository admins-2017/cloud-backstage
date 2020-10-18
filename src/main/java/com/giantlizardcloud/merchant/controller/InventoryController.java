package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.poi.util.FileUtils;
import com.giantlizardcloud.merchant.dto.QueryInventory;
import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.merchant.service.IInventoryService;
import com.giantlizardcloud.merchant.service.IShopService;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;
import com.giantlizardcloud.merchant.vo.InventoryGetCommodityClassVo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final IInventoryService inventoryService;
    private final IShopService shopService;

    public InventoryController(IInventoryService inventoryService, IShopService shopService
    ) {
        this.inventoryService = inventoryService;
        this.shopService = shopService;
    }

    @GetMapping("/status/{status}")
    public JSONResult getDetailByIndex(@PathVariable Integer status, Long shopId) {

        if (status == 1) {
            List<InventoryGetCommodityClassVo> vos = inventoryService.getInventoryCommodity(shopId);
            return JSONResult.ok(vos);
        } else if (status == 2) {
            List<CommodityWithShopVo> vos = inventoryService.getZeroInventory(shopId);
            return JSONResult.ok(vos);
        } else if (status == 3) {
            List<CommodityWithShopVo> vos = inventoryService.getWarnInventory(shopId);
            return JSONResult.ok(vos);
        } else {
            List<CommodityWithShopVo> vos = inventoryService.getAmpleInventory(shopId);
            return JSONResult.ok(vos);
        }
    }

    /**
     * 查询商铺下所有商品的库存
     *
     * @return 返回json格式的查询结果
     */
    @GetMapping
    public JSONResult getCommodityByShop(QueryInventory query) {
        return JSONResult.ok(inventoryService.getShopUnderCommodity(query));
    }

    @GetMapping("/export/{status}")
    public void ExportInventory(HttpServletResponse response, @PathVariable Integer status, Long shopId) {
        String name = "";
        if (status == 0) {
            List<CommodityWithShopVo> vos = inventoryService.exportAllCommodityByShopId(shopId);
            if (vos.size()!=0){
                if (shopId != null) {
                    name = vos.get(0).getShopName()+"-商品明细";
                }else {
                    name = "商品明细";
                }
                FileUtils.exportExcel(vos, name, "", CommodityWithShopVo.class, name+".xls", response);
            }
        } else if (status == 1) {
            List<InventoryGetCommodityClassVo> vos = inventoryService.getInventoryCommodity(shopId);
            if (vos.size()!=0){
                if (shopId != null) {
                    Shop shop  = shopService.getOne(new QueryWrapper<Shop>().select("shop_name").eq("shop_id", shopId));
                    name = shop.getShopName()+"-商品种类明细";
                }else {
                    name = "商品种类明细";
                }
                FileUtils.exportExcel(vos, name, "", InventoryGetCommodityClassVo.class, name+".xls", response);
            }
        } else if (status == 2) {
            List<CommodityWithShopVo> vos = inventoryService.getZeroInventory(shopId);
            if (vos.size()!=0){
                if (shopId != null) {
                    name = vos.get(0).getShopName()+"-缺货商品明细";
                }else {
                    name = "缺货商品明细";
                }
                FileUtils.exportExcel(vos, name, "", CommodityWithShopVo.class, name+".xls", response);
            }
        } else if (status == 3) {
            List<CommodityWithShopVo> vos = inventoryService.getWarnInventory(shopId);
            if (vos.size()!=0){
                if (shopId != null) {
                    name = vos.get(0).getShopName()+"-库存不足明细";
                }else {
                    name = "库存不足明细";
                }
                FileUtils.exportExcel(vos, name, "", CommodityWithShopVo.class, name+".xls", response);
            }
        } else {
            List<CommodityWithShopVo> vos = inventoryService.getAmpleInventory(shopId);
            if (vos.size()!=0){
                if (shopId != null) {
                    name = vos.get(0).getShopName()+"-库存充足明细";
                }else {
                    name = "库存充足明细";
                }
                FileUtils.exportExcel(vos, name, "", CommodityWithShopVo.class, name+".xls", response);
            }
        }
    }
}
