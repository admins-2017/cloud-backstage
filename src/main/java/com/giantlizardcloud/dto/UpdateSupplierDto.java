package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class UpdateSupplierDto {

    @ApiModelProperty(value = "供应商id",example = "1")
    private Long supplierId;

    @ApiModelProperty(value = "供应商名字")
    private String supplierName;

    @ApiModelProperty(value = "供应商注册资金",example = "1")
    private Double supplierCapital;

    @ApiModelProperty(value = "供应生产地址")
    private String supplierAddress;

    @ApiModelProperty(value = "供应商负责人")
    private String supplierPerson;

    @ApiModelProperty(value = "供应商联系电话")
    private String supplierTelephone;

    @ApiModelProperty(value = "供应商电子邮箱")
    private String supplierEmail;

    @ApiModelProperty(value = "供应商主营业务")
    private String supplierBusiness;

    @ApiModelProperty(value = "与供应商曾是否合作   0为未合作   1为合作",example = "1")
    private Boolean supplierCooperated;

    @ApiModelProperty(value = "供应商   0为删除  1为未删除")
    private Boolean supplierStatus;
}


