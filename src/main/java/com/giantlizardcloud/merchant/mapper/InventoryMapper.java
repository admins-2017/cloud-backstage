package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-10-09
 */
public interface InventoryMapper extends BaseMapper<Inventory> {

    @Select("SELECT mi.inventory_id,mi.commodity_id,mi.shop_id,mi.inventory_number,mc.commodity_name,mc.commodity_number,mc.commodity_picture,mc.commodity_selling_price,mc.commodity_unit,mc.commodity_status,mc.commodity_description,mc.classification_id,ms.shop_name ,ms.shop_address , mcl.classification_name FROM merchant_inventory mi\n" +
            " LEFT JOIN merchant_commodity mc ON mi.commodity_id = mc.commodity_id\n" +
            " LEFT JOIN merchant_shop ms ON mi.shop_id = ms.shop_id \n" +
            " LEFT JOIN merchant_classification mcl ON mc.classification_id = mcl.classification_id\n" +
            "WHERE mi.shop_id = #{shopId}")
    List<CommodityWithShopVo> getShopUnderCommodity(@Param("shopId") Long shopId);
}
