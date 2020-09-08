package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-08-20
 */
public interface ShopMapper extends BaseMapper<Shop> {

    List<Shop> selectAllShopWithUser(@Param("shopName") String shopName,@Param("page") Integer page, @Param("size") Integer size);
}
