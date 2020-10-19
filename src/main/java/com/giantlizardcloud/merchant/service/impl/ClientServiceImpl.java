package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.entity.Client;
import com.giantlizardcloud.merchant.mapper.ClientMapper;
import com.giantlizardcloud.merchant.service.IClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.ClientWithShopVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-24
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {


    @Override
    public IPage<ClientWithShopVo> getAllClientWithShop(Integer page, Integer size) {

        Page<ClientWithShopVo> voPagepage = new Page<>(page,size);

        return this.baseMapper.getAllClientByPage(voPagepage);
    }

  /* public IPage<Client> getAllClientByPage(Integer page, Integer size){

        Page<Client> clientPage = new Page<Client>(page,size);

        return  this.baseMapper.getAllClientByPage(clientPage);
   }
   */

}
