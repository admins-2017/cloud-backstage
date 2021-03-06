package com.giantlizardcloud.merchant.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;
import com.giantlizardcloud.merchant.vo.InventoryAndCommodityVo;
import com.giantlizardcloud.merchant.vo.InventoryGetCommodityClassVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-10-09
 */
public interface InventoryMapper extends BaseMapper<Inventory> {

    IPage<CommodityWithShopVo> getShopUnderCommodity(Page<CommodityWithShopVo> page ,@Param("shopId") Long shopId);

    Integer getCountByCommodity(@Param("shopId") Long shopId);

    /**
     * 库存为0 缺货
     * @param shopId 商铺id
     * @return 统计数量结果
     */
    Integer getCountByOutOfStock(@Param("shopId") Long shopId);

    /**
     * 库存警告 低于10
     * @param shopId 商铺id
     * @return 统计数量结果
     */
    Integer getCountByInventoryWarn(@Param("shopId") Long shopId);

    /**
     * 库存总量
     * @param shopId 商铺id
     * @return 统计数量结果
     */
    Integer getCountInventoryNumber(@Param("shopId") Long shopId);

    /**
     * @param shopId 商铺id
     * @return 统计数量结果
     * @return
     */
    List<InventoryGetCommodityClassVo> getInventoryCommodity(@Param("shopId") Long shopId);

    List<CommodityWithShopVo> getZeroInventory(@Param("shopId") Long shopId);

    List<CommodityWithShopVo> getWarnInventory(@Param("shopId") Long shopId);

    List<CommodityWithShopVo> getAmpleInventory(@Param("shopId") Long shopId);

    List<CommodityWithShopVo> exportAllCommodityByShopId(@Param("shopId") Long shopId);

    /**
     * 根据商铺id 获取商品信息
     * @param shopId 商铺id
     * @return 结果集
     */
    @Select("SELECT mi.inventory_id,mi.inventory_number,mi.commodity_id,mc.commodity_name,mc.commodity_number,mc.commodity_selling_price,mc.commodity_unit FROM merchant_inventory mi\n" +
            "\tLEFT JOIN merchant_commodity mc ON mi.commodity_id = mc.commodity_id\n" +
            "\tWHERE mi.inventory_number > 0 and shop_id = #{shopId}")
    List<InventoryAndCommodityVo> getCommodityByShopId(@Param("shopId") Long shopId);
}
