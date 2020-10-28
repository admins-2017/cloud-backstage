package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.dto.QueryOrderByConditionDto;
import com.giantlizardcloud.merchant.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.OrderAndClientAndUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-10-27
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 分页获取订单及详情
     * @param page 页码
     * @param size 条数
     * @param status 状态
     * @return 结果集
     */
    List<OrderAndClientAndUserVO> getPage(@Param("page") Integer page, @Param("size") Integer size,@Param("status") Integer status);

    /**
     * 查询订单是否结清
     * @param orderId 订单id
     * @return 查询未结清金额
     */
    @Select("SELECT order_unpaid_amount FROM merchant_order WHERE order_id = #{orderId}")
    Double selectUnpaidAmount(@Param("orderId") Long orderId);

    /**
     * 根据查询条件分页获取订单及详情
     * @param dto 查询条件
     * @return 结果集
     */
    List<OrderAndClientAndUserVO> getPageByCondition(QueryOrderByConditionDto dto);
}
