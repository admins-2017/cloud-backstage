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

    /**
     * 根据状态查询供应商
     * @return 供应商结果集
     */
    List<SupplierVo> getAllSupplierBySupplierStatus();

    /**
     * 根据供应商分页
     * @param supplierName 供应商名
     * @param page 页码
     * @param size 条数
     * @return
     */
    IPage<Supplier> selectAllSupplierWithUser(String supplierName, Integer page, Integer size);

}
