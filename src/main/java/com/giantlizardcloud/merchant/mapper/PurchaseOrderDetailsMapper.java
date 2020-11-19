package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.entity.PurchaseOrderDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.PurchaseOrderDetailsVo;
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
public interface PurchaseOrderDetailsMapper extends BaseMapper<PurchaseOrderDetails> {

    /**
     * 根据订单id获取采购订单详情
     * @param orderId
     * @return
     */
    List<PurchaseOrderDetailsVo> getDetailsByOrderId(@Param("orderId") Long orderId);

}
