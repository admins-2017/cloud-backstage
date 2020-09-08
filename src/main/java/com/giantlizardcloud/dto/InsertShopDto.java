package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InsertShopDto {

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    private String shopAddress;

    @ApiModelProperty(value = "商铺联系电话")
    private String shopTel;

    @ApiModelProperty(value = "商铺介绍/公告")
    private String shopIntroduction;
}
