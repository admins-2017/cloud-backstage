package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VerifyCodeDto {

    @ApiModelProperty(value = "验证码id")
    private String uuid;

    @ApiModelProperty(value = "验证结果",example = "1")
    private Integer result;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
}
