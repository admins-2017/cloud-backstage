package com.giantlizardcloud.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleWithUserVo {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    private String status;

    @ApiModelProperty(value = "用户住址")
    private String userDetailsAddr;

    @ApiModelProperty(value = "用户联系方式")
    private String userDetailsTel;

    private Long shopName;

}
