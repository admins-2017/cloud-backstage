package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuDto {

    @ApiModelProperty(value = "ID")
    private Long menuId;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "前端跳转URL")
    private String path;

    @ApiModelProperty(value = "权限icon图标")
    private String iCon;

    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单类型 （类型   0：目录   1：菜单   2：按钮）")
    private Integer type;
}
