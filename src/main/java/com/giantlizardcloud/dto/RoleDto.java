package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto {

    @ApiModelProperty(value = "角色id",example = "1")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色介绍")
    private String roleDescription;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    private List<String> menuList;
}
