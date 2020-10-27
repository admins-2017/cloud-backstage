package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.merchant.dto.AddOrderAndDetailDto;
import com.giantlizardcloud.merchant.entity.Client;
import com.giantlizardcloud.merchant.entity.Order;
import com.giantlizardcloud.merchant.mapper.OrderMapper;
import com.giantlizardcloud.merchant.service.IClientService;
import com.giantlizardcloud.merchant.service.IInventoryService;
import com.giantlizardcloud.merchant.service.IOrderDetailsService;
import com.giantlizardcloud.merchant.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.OrderAndClientAndUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-10-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final IOrderDetailsService detailsService;
    private final IInventoryService inventoryService;
    private final IClientService clientService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrderAndOrderDetails(AddOrderAndDetailDto dto) {
        Order order = new Order();
        BeanUtils.copyProperties(dto, order);
        long orderId = new IdWorker().nextId();
        order.setOrderId(orderId);
        this.save(order);
        dto.getDetails().forEach((details -> {
            inventoryService.deductInventory(details.getShopId(), details.getCommodityId(), details.getOrderDetailNumber());
            details.setOrderId(orderId);
            detailsService.save(details);
        }));
        /* 判断是否结清,未结清则给客户增加欠款 */
        if (0 < dto.getOrderUnpaidAmount()) {
            clientService.update(null, new UpdateWrapper<Client>().setSql("client_consumption = client_consumption +"
                    + dto.getOrderUnpaidAmount()).eq("client_id", dto.getClientId()));
        }
    }

    @Override
    public Page<OrderAndClientAndUserVO> getPage(Integer page, Integer size, Integer status) {
        Integer newPage = (page - 1) * size;
        List<OrderAndClientAndUserVO> list = this.baseMapper.getPage(newPage, size,status);
        Page<OrderAndClientAndUserVO> vos = new Page<>(page, size);
        Integer total = this.baseMapper.selectCount(new QueryWrapper<Order>().eq("order_status", status));
        vos.setTotal(total);
        vos.setRecords(list);
        vos.setPages(page);
        vos.setSize(size);
        if ((total%size)==0){
            vos.setCurrent(total/size);
        }else {
            vos.setCurrent((total/size)+1);
        }
        return vos;
    }

    public OrderServiceImpl(IOrderDetailsService detailsService, IInventoryService inventoryService, IClientService clientService) {
        this.detailsService = detailsService;
        this.inventoryService = inventoryService;
        this.clientService = clientService;
    }
}
