package com.giantlizardcloud.merchant.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderDetailsVo {

    @ApiModelProperty(value = "订单详情id",example = "1")
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

    @ApiModelProperty(value = "商品名")
    private String commodityName;

    @ApiModelProperty(value = "商品编码")
    private String commodityNumber;

    @ApiModelProperty(value = "商品单位(包，份)")
    private String commodityUnit;

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "销售单价")
    private Double unitPrice;
}
