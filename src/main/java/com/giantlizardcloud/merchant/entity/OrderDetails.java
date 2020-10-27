package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_order_details")
@ApiModel(value="OrderDetails对象", description="详情对象")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单详情id",example = "1")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderDetailId;

    @ApiModelProperty(value = "订单id",example = "1")
    private Long orderId;

    @ApiModelProperty(value = "详情商品id",example = "1")
    private Integer commodityId;

    @ApiModelProperty(value = "商品售出仓库id",example = "1")
    private Long shopId;

    @ApiModelProperty(value = "订单商品数量",example = "1")
    private Integer orderDetailNumber;

    @ApiModelProperty(value = "订单详情价格")
    private Double orderDetailPrice;



}
