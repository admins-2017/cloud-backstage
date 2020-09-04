package com.giantlizardcloud.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleWithUserVo {

    @ApiModelProperty(value = "角色ID",example = "1")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "用户ID",example = "1")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "用户住址")
    private String userDetailsAddr;

    @ApiModelProperty(value = "用户联系方式")
    private String userDetailsTel;

    @ApiModelProperty(value = "商铺名称")
    private Long shopName;

}
