package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.dto.QueryPurchaseOrderDto;
import com.giantlizardcloud.merchant.entity.PurchaseOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.PurchasePageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-11-17
 */
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrder> {

    /**
     * 根据条件分页查询
     * @param newPage 页码
     * @param size 条数
     * @param status 状态
     * @return 订单详情集合
     */
    List<PurchasePageVo> findOrderByPage(@Param("page") Integer newPage, @Param("size") Integer size, @Param("status") Integer status);

    /**
     * 根据条件进行分页查询
     * @param dto 查询条件
     * @return 订单详情集合
     */
    List<PurchasePageVo> findOrderByCondition(QueryPurchaseOrderDto dto);

    /**
     * 根据条件进行统计
     * @param dto 查询条件
     * @return 统计结果
     */
    Integer countOrderByCondition(QueryPurchaseOrderDto dto);
}
