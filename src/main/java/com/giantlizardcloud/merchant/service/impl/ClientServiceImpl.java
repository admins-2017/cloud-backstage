package com.giantlizardcloud.merchant.service.impl;

import com.giantlizardcloud.merchant.entity.Client;
import com.giantlizardcloud.merchant.mapper.ClientMapper;
import com.giantlizardcloud.merchant.service.IClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-10-19
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

    @Override
    public void minusClientArrears(Long clientId, Double settleSum) {

    }

    @Override
    public void addClientArrears(long clientId, Double settleSum) {

    }
}
