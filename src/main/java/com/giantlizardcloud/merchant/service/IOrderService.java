package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.dto.AddOrderAndDetailDto;
import com.giantlizardcloud.merchant.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.InitOrderVo;
import com.giantlizardcloud.merchant.vo.OrderAndClientAndUserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-10-27
 */
public interface IOrderService extends IService<Order> {

    /**
     * 订单添加及订单详情添加
     * @param dto 订单信息
     */
    void addOrderAndOrderDetails(AddOrderAndDetailDto dto);

    /**
     * 获取订单分页信息
     * @param page 页码
     * @param size 条数
     * @param status 状态
     * @return 订单及详情和客户和用户结果集
     */
    Page<OrderAndClientAndUserVO> getPage(Integer page, Integer size, Integer status);

    /**
     * 作废订单
     * @param orderId 订单id
     */
    void invalidOrder(Long orderId);

    /**
     * 销售退货单及详情退回
     * @param dto
     */
    void returnedOrder(AddOrderAndDetailDto dto);

    /**
     * 初始化销售单
     * @return 销售单vo
     */
    InitOrderVo initOrder();
}
