package com.giantlizardcloud.merchant.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.merchant.entity.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.vo.ClientWithShopVo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-24
 */
public interface ClientMapper extends BaseMapper<Client> {

    /*
    *  select c.*,s.* from merchant_client c LEFT JOIN merchant_shop s on c.shop_id = s.shop_id
    * */

    @Select("select c.*,s.* from merchant_client c \n"+
              "LEFT JOIN merchant_shop s on c.shop_id = s.shop_id")
    IPage<ClientWithShopVo> getAllClientByPage(Page<ClientWithShopVo> clientPage);
}
