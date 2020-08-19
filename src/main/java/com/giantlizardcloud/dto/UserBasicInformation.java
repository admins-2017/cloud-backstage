package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserBasicInformation implements Serializable {

    @ApiModelProperty(value = "用户性别 1 男 2女")
    private Integer userDetailsSex;

    @ApiModelProperty(value = "用户住址")
    private String userDetailsAddr;

    @ApiModelProperty(value = "商铺id")
    private Integer shopId;

    private String oldPassword;

    private String newPassword;
}
