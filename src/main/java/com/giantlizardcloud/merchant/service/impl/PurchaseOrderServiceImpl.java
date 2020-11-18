package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.merchant.dto.AddPurchaseOrderDto;
import com.giantlizardcloud.merchant.entity.PurchaseOrder;
import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.merchant.entity.Supplier;
import com.giantlizardcloud.merchant.mapper.PurchaseOrderMapper;
import com.giantlizardcloud.merchant.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.InitPurchaseOrderVo;
import com.giantlizardcloud.sys.entity.User;
import com.giantlizardcloud.sys.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 采购单操作
 * </p>
 *
 * @author jobob
 * @since 2020-11-17
 */
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements IPurchaseOrderService {

    private final ISupplierService supplierService;
    private final IUserService userService;
    private final IShopService shopService;
    private final IPurchaseOrderDetailsService detailsService;
    private final IInventoryService inventoryService;

    @Override
    public InitPurchaseOrderVo init() {
        return new InitPurchaseOrderVo("cgd-" + new IdWorker().nextId(),
                supplierService.list(new QueryWrapper<Supplier>().eq("supplier_status",1)),
                shopService.list(new QueryWrapper<Shop>().le("shop_status",2)),
                userService.list(new QueryWrapper<User>().select("user_id","username").eq("status","NORMAL")));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePurchaseOrder(AddPurchaseOrderDto dto) {
        Long orderId = new IdWorker().nextId();
        /*
        保存采购单
         */
        PurchaseOrder order = new PurchaseOrder();
        BeanUtils.copyProperties(dto,order);
        order.setPurchaseId(orderId);
        save(order);
        /*
          保存采购明细
         */
        dto.getDetails().forEach( detail -> {
            /*
            将采购商品入库
             */
            inventoryService.increaseInventory(detail.getShopId(),detail.getCommodityId(),detail.getPurchaseDetailNumber());
            /*
            保存明细
             */
            detail.setPurchaseId(orderId);
            detailsService.save(detail);
        });
        if (dto.getPurchaseUnpaidAmount() > 0){
            supplierService.update(new UpdateWrapper<Supplier>().eq("supplier_id",dto.getSupplierId())
                    .setSql(" supplier_arrears = supplier_arrears +"+dto.getPurchaseUnpaidAmount()));
        }
    }

    @Override
    public void returnPurchaseOrder(AddPurchaseOrderDto dto) {
        Long orderId = new IdWorker().nextId();
        /*
        保存采购退货单
         */
        PurchaseOrder order = new PurchaseOrder();
        BeanUtils.copyProperties(dto,order);
        order.setPurchaseId(orderId);
        save(order);
        dto.getDetails().forEach( detail -> {
            /*
            将采购商品入库
             */
            inventoryService.deductInventory(detail.getShopId(),detail.getCommodityId(),detail.getPurchaseDetailNumber());
            /*
            保存明细
             */
            detail.setPurchaseId(orderId);
            detailsService.save(detail);
        });
        /*
        采购退货单 已结清的减掉所欠供应商货款
         */
        if (dto.getPurchaseActualPayment() > 0){
            supplierService.update(new UpdateWrapper<Supplier>().eq("supplier_id",dto.getSupplierId())
                    .setSql(" supplier_arrears = supplier_arrears -"+dto.getPurchaseActualPayment()));
        }
    }

    @Override
    public void invalidOrder(Long orderId) {
        PurchaseOrder order = getOne(new QueryWrapper<PurchaseOrder>().eq("purchase_id", orderId));
        /*
        作废 将未结清所欠供应商货款减去
         */


    }

    public PurchaseOrderServiceImpl(ISupplierService supplierService, IUserService userService,
                                    IShopService shopService , IPurchaseOrderDetailsService purchaseOrderDetailsService, IInventoryService inventoryService) {
        this.supplierService = supplierService;
        this.userService = userService;
        this.shopService = shopService;
        this.detailsService = purchaseOrderDetailsService;
        this.inventoryService = inventoryService;
    }
}
