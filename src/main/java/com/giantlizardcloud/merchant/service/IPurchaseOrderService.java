package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.dto.AddPurchaseOrderDto;
import com.giantlizardcloud.merchant.dto.QueryPurchaseOrderDto;
import com.giantlizardcloud.merchant.entity.PurchaseOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.InitPurchaseOrderVo;
import com.giantlizardcloud.merchant.vo.PurchasePageVo;

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

    /**
     * 作废订单并还原订单库存
     * @param orderId 订单id
     */
    void invalidOrder(Long orderId);

    /**
     * 根据状态分页查询
     * @param page 页码
     * @param size 条数
     * @param status 状态
     * @return 订单详情结果集
     */
    Page<PurchasePageVo> getOrderByPage(Integer page, Integer size, Integer status);

    /**
     * 根据查询条件分页查询
     * @param dto 查询条件
     * @return 订单详情结果集
     */
    Page<PurchasePageVo> getOrderByCondition(QueryPurchaseOrderDto dto);
}
