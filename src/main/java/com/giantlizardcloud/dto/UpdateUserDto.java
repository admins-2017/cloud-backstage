package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUserDto {

    @ApiModelProperty(value = "用户id",example = "1")
    private Long userId;

    @ApiModelProperty(value = "用户性别",example = "1")
    private Integer userDetailsSex;

    @ApiModelProperty(value = "用户地址")
    private String userDetailsAddr;

    @ApiModelProperty(value = "用户邮箱")
    private String userDetailsMail;

    @ApiModelProperty(value = "商铺id",example = "1")
    private Long shopId;

    @ApiModelProperty(value = "角色id",example = "1")
    private Long roleId;
}
