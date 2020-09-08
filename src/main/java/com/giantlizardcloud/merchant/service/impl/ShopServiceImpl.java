package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.merchant.mapper.ShopMapper;
import com.giantlizardcloud.merchant.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-20
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Override
    public IPage<Shop> selectAllShopWithUser(String shopName,Integer page,Integer size) {
        page = (page - 1) * size;
        List<Shop> shops = this.baseMapper.selectAllShopWithUser(shopName,page, size);
        IPage<Shop> shopIPage = new Page<>();
        shopIPage.setRecords(shops);
        if (null == shopName){
            shopIPage.setTotal(this.baseMapper.selectCount(new QueryWrapper<>()));
        }else{
            shopIPage.setTotal(this.baseMapper.selectCount(new QueryWrapper<Shop>().like("shop_name",shopName)));
        }
        return shopIPage;
    }
}
