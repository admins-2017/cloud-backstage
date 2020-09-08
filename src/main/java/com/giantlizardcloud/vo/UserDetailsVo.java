package com.giantlizardcloud.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDetailsVo {
    @ApiModelProperty(value = "用户ID",example = "1")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户性别 1 男 2女",example = "1")
    private Integer userDetailsSex;

    @ApiModelProperty(value = "用户住址")
    private String userDetailsAddr;

    @ApiModelProperty(value = "用户邮箱")
    private String userDetailsMail;

    @ApiModelProperty(value = "用户联系方式")
    private String userDetailsTel;

}
