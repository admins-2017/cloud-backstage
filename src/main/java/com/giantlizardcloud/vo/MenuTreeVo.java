package com.giantlizardcloud.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 返回生成功能栏
 * @author kang
 */
@Data
public class MenuTreeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID",example = "1")
    private Long menuId;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "前端跳转URL")
    private String path;

    @ApiModelProperty(value = "权限icon图标")
    private String iCon;

    @ApiModelProperty(value = "父菜单ID",example = "1")
    private Long parentId;

    @ApiModelProperty(value = "菜单类型 （类型   0：目录   1：菜单   2：按钮）",example = "1")
    private Integer type;

    private List<MenuTreeVo> children = new ArrayList<MenuTreeVo>();
}

