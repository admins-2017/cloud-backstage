package com.giantlizardcloud.merchant.service.impl;

import com.giantlizardcloud.merchant.entity.Inventory;
import com.giantlizardcloud.merchant.mapper.InventoryMapper;
import com.giantlizardcloud.merchant.service.IInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
