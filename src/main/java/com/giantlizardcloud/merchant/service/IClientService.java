package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.merchant.entity.Client;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.ClientWithShopVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-24
 */
public interface IClientService extends IService<Client> {

    IPage<ClientWithShopVo> getAllClientWithShop(Integer page, Integer size);

}
