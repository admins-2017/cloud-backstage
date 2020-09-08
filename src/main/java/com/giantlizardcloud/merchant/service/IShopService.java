package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-20
 */
public interface IShopService extends IService<Shop> {

    IPage<Shop> selectAllShopWithUser(String shopName,Integer page,Integer size);
}
