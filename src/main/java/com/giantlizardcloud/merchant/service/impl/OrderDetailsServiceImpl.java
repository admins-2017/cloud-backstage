package com.giantlizardcloud.merchant.service.impl;

import com.giantlizardcloud.merchant.entity.OrderDetails;
import com.giantlizardcloud.merchant.mapper.OrderDetailsMapper;
import com.giantlizardcloud.merchant.service.IOrderDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-10-27
 */
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails> implements IOrderDetailsService {

}
