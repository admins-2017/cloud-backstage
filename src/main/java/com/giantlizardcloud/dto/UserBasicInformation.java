package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserBasicInformation implements Serializable {

    @ApiModelProperty(value = "用户性别 1 男 2女",example = "1")
    private Integer userDetailsSex;

    @ApiModelProperty(value = "用户住址")
    private String userDetailsAddr;

    @ApiModelProperty(value = "商铺id",example = "1")
    private Integer shopId;

    @ApiModelProperty(value = "原始密码")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
