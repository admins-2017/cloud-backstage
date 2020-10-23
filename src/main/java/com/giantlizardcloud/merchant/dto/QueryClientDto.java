package com.giantlizardcloud.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 */
@Data
public class QueryClientDto {

    @ApiModelProperty(value = "名字")
    private String clientName;

    @ApiModelProperty(value = "电话号")
    private String clientPhone;

    @ApiModelProperty(value = "客户邮箱")
    private String clientEmail;

    @ApiModelProperty(value = "客户状态 0 为删除  1为正常",example = "0")
    private Boolean clientStatus;

    @ApiModelProperty(value = "页码",example = "1")
    private Integer page;

    @ApiModelProperty(value = "条数",example = "10")
    private Integer size;
}
