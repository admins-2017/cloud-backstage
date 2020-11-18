package com.giantlizardcloud.merchant.service;

import com.giantlizardcloud.merchant.dto.AddPurchaseOrderDto;
import com.giantlizardcloud.merchant.entity.PurchaseOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.InitPurchaseOrderVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-11-17
 */
public interface IPurchaseOrderService extends IService<PurchaseOrder> {

    /**
     * 初始化采购单
     * @return 订单编号 供应商集合 采购员集合 商铺集合
     */
    InitPurchaseOrderVo init();

    /**
     * 保存采购单信息
     * @param dto 采购单明细
     */
    void savePurchaseOrder(AddPurchaseOrderDto dto);

    /**
     * 保存采购退货单
     * @param dto 采购退货单明细
     */
    void returnPurchaseOrder(AddPurchaseOrderDto dto);
}
