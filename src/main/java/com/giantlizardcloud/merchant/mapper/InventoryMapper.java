package com.giantlizardcloud.merchant.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.dto.QueryInventory;
import com.giantlizardcloud.merchant.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;
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
     * @param shopId
     * @return
     */
    Integer getCountByOutOfStock(@Param("shopId") Long shopId);

    /**
     * 库存警告 低于10
     * @param shopId
     * @return
     */
    Integer getCountByInventoryWarn(@Param("shopId") Long shopId);

    /**
     * 库存总量
     * @param shopId
     * @return
     */
    Integer getCountInventoryNumber(@Param("shopId") Long shopId);

}
