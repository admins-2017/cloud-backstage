package com.giantlizardcloud.merchant.service.impl;

import com.giantlizardcloud.merchant.entity.Inventory;
import com.giantlizardcloud.merchant.mapper.InventoryMapper;
import com.giantlizardcloud.merchant.service.IInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-10-09
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

    @Override
    public List<CommodityWithShopVo> getShopUnderCommodity(Long shopId) {
        return this.baseMapper.getShopUnderCommodity(shopId);
    }
}
