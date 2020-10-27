package com.giantlizardcloud.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderDetailDto {

    @ApiModelProperty(value = "详情商品id",example = "1")
    private Integer commodityId;

    @ApiModelProperty(value = "订单商品数量",example = "1")
    private Integer orderDetailNumber;

    @ApiModelProperty(value = "订单详情价格")
    private Double orderDetailPrice;
}
