package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopDto {
    @ApiModelProperty(value = "商铺id")
    private Long shopId;

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    private String shopAddress;

    @ApiModelProperty(value = "1:正常 2:休息 3:作废",example = "1")
    private Integer shopStatus;
}
