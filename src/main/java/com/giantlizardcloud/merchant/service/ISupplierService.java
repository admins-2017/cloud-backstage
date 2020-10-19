package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.entity.Supplier;
import com.giantlizardcloud.vo.SupplierVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
public interface ISupplierService extends IService<Supplier> {

    List<SupplierVo> getAllSupplierBySupplierStatus();

    IPage<Supplier> selectAllSupplierWithUser(String supplierName, Integer page, Integer size);

}
