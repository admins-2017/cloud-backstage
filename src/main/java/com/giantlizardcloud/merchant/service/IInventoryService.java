package com.giantlizardcloud.merchant.service;

import com.giantlizardcloud.merchant.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;

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

    List<CommodityWithShopVo> getShopUnderCommodity(Long shopId);
}
