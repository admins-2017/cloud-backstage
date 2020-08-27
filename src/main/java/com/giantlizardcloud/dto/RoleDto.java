package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto {

    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    private String roleDescription;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    private List<Long> menuList;
}
