package com.giantlizardcloud.merchant.service;

import com.giantlizardcloud.merchant.dto.QueryInventory;
import com.giantlizardcloud.merchant.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;
import com.giantlizardcloud.merchant.vo.InventoryGetCommodityClassVo;
import com.giantlizardcloud.merchant.vo.InventoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-10-09
 */
public interface IInventoryService extends IService<Inventory> {

    /**
     * 获取商铺下的商品信息
     * @param query 查询信息
     * @return 库存明细
     */
    InventoryVo getShopUnderCommodity(QueryInventory query);
    /**
     * 获取库存下所有商品类别
     * @param shopId 商铺id
     * @return 商品类别集合
     */
    List<InventoryGetCommodityClassVo> getInventoryCommodity(Long shopId);
    /**
     * 获取库存为0的商品信息
     * @param shopId 商铺id
     * @return 商品集合
     */
    List<CommodityWithShopVo> getZeroInventory(Long shopId);
    /**
     * 获取库存警告的商品信息
     * @param shopId 商铺id
     * @return 商品集合
     */
    List<CommodityWithShopVo> getWarnInventory(Long shopId);

    /**
     * 获取库存存足的商品信息
     * @param shopId 商铺id
     * @return 商品集合
     */
    List<CommodityWithShopVo> getAmpleInventory(Long shopId);

    /**
     * 导出仓库下所有商品信息
     * @param shopId 商铺id
     * @return 商品集合
     */
    List<CommodityWithShopVo> exportAllCommodityByShopId(Long shopId);

    /**
     * 扣除库存
     * @param shopId 商铺id
     * @param commodityId 商品id
     * @param orderDetailNumber 扣除数量
     */
    void deductInventory(Long shopId, Integer commodityId, Integer orderDetailNumber);
}
