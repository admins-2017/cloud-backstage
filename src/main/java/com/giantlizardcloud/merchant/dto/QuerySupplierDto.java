package com.giantlizardcloud.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 */
@Data
public class QuerySupplierDto {

    @ApiModelProperty(value = "供应商名字")
    private String supplierName;

    @ApiModelProperty(value = "供应商联系电话")
    private String supplierTelephone;

    @ApiModelProperty(value = "供应商电子邮箱")
    private String supplierEmail;

    @ApiModelProperty(value = "供应商   0为删除  1为未删除",example = "0")
    private Boolean supplierStatus;

    @ApiModelProperty(value = "页码",example = "1")
    private Integer page;

    @ApiModelProperty(value = "条数",example = "10")
    private Integer size;
}
