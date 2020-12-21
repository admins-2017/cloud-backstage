package com.giantlizardcloud.merchant.service;

import com.giantlizardcloud.merchant.entity.Client;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-10-19
 */
public interface IClientService extends IService<Client> {

    /**
     * 减去客户欠款
     * @param clientId 客户id
     * @param settleSum 减去金额
     */
    void minusClientArrears(Long clientId, Double settleSum);

    /**
     * 增加客户欠款
     * @param clientId 客户id
     * @param settleSum 增加金额
     */
    void addClientArrears(long clientId, Double settleSum);
}
