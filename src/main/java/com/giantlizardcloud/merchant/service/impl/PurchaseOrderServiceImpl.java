package com.giantlizardcloud.merchant.service.impl;

import com.giantlizardcloud.merchant.entity.PurchaseOrder;
import com.giantlizardcloud.merchant.mapper.PurchaseOrderMapper;
import com.giantlizardcloud.merchant.service.IPurchaseOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-11-17
 */
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements IPurchaseOrderService {

}
