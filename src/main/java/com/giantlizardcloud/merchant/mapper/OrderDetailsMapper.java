package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.entity.OrderDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.OrderDetailsVo;
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
public interface OrderDetailsMapper extends BaseMapper<OrderDetails> {


    List<OrderDetailsVo> getDetailByOrderId(@Param("orderId") Long orderId);

}
