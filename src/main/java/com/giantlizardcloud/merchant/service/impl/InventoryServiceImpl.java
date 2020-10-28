package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.dto.QueryInventory;
import com.giantlizardcloud.merchant.entity.Inventory;
import com.giantlizardcloud.merchant.mapper.InventoryMapper;
import com.giantlizardcloud.merchant.service.IInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.CommodityWithShopVo;
import com.giantlizardcloud.merchant.vo.InventoryAndCommodityVo;
import com.giantlizardcloud.merchant.vo.InventoryGetCommodityClassVo;
import com.giantlizardcloud.merchant.vo.InventoryVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-10-09
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

    @Override
    public InventoryVo getShopUnderCommodity(QueryInventory query) {
        Page<CommodityWithShopVo> commodityWithShopVoPage = new Page<>(query.getPage(), query.getSize());
        InventoryVo vo = new InventoryVo();
        vo.setVos(this.baseMapper.getShopUnderCommodity(commodityWithShopVoPage, query.getShopId()));
        vo.setCommodityNumber(this.baseMapper.getCountByCommodity(query.getShopId()));
        vo.setStockNumber(this.baseMapper.getCountByOutOfStock(query.getShopId()));
        vo.setWarnNumber(this.baseMapper.getCountByInventoryWarn(query.getShopId()));
        vo.setInventoryNumber(this.baseMapper.getCountInventoryNumber(query.getShopId()));
        return vo;
    }

    @Override
    public List<InventoryGetCommodityClassVo> getInventoryCommodity(Long shopId) {
        return this.baseMapper.getInventoryCommodity(shopId);
    }

    @Override
    public List<CommodityWithShopVo> getZeroInventory(Long shopId) {
        return this.baseMapper.getZeroInventory(shopId);
    }

    @Override
    public List<CommodityWithShopVo> getWarnInventory(Long shopId) {
        return this.baseMapper.getWarnInventory(shopId);
    }

    @Override
    public List<CommodityWithShopVo> getAmpleInventory(Long shopId) {
        return this.baseMapper.getAmpleInventory(shopId);
    }

    @Override
    public List<CommodityWithShopVo> exportAllCommodityByShopId(Long shopId) {
        return this.baseMapper.exportAllCommodityByShopId(shopId);
    }

    @Override
    public void deductInventory(Long shopId, Integer commodityId, Integer orderDetailNumber) {
        this.baseMapper.update(null, new UpdateWrapper<Inventory>().setSql(" inventory_number = inventory_number -" + orderDetailNumber)
                .eq("shop_id", shopId).eq("commodity_id", commodityId));
    }

    @Override
    public void increaseInventory(Long shopId, Integer commodityId, Integer orderDetailNumber) {
        this.baseMapper.update(null, new UpdateWrapper<Inventory>().setSql(" inventory_number = inventory_number +" + orderDetailNumber)
                .eq("shop_id", shopId).eq("commodity_id", commodityId));
    }

    @Override
    public List<InventoryAndCommodityVo> getCommodityByShopId(Long shopId) {
        return this.baseMapper.getCommodityByShopId(shopId);
    }
}
