package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.merchant.dto.QueryInventory;
import com.giantlizardcloud.merchant.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;
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

    InventoryVo getShopUnderCommodity(QueryInventory query);
}
