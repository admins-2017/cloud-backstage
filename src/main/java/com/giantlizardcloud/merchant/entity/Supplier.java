package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_supplier")
@ApiModel(value="Supplier对象", description="")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "与供应商曾是否合作   1为未合作   0为合作",example = "0")
    private Boolean supplierCooperated;

    @ApiModelProperty(value = "供应商   1为删除  0为未删除",example = "0")
    private Boolean supplierStatus;

    @ApiModelProperty(value = "预留商铺id",example = "1")
    private Long shopId;

}
