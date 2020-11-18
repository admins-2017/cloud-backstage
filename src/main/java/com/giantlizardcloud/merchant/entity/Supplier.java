package com.giantlizardcloud.merchant.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
    @Excel(name = "供应商系统编号", width = 30)
    private Long supplierId;

    @ApiModelProperty(value = "供应商名字")
    @Excel(name = "供应商名字" , width = 30)
    private String supplierName;

    @ApiModelProperty(value = "供应商注册资金",example = "1")
    @Excel(name = "供应商注册资金")
    private Double supplierCapital;

    @ApiModelProperty(value = "供应生产地址")
    @Excel(name = "供应生产地址", width = 40)
    private String supplierAddress;

    @ApiModelProperty(value = "供应商负责人")
    @Excel(name = "供应商负责人")
    private String supplierPerson;

    @ApiModelProperty(value = "供应商联系电话")
    @Excel(name = "供应商联系电话", width = 30)
    private String supplierTelephone;

    @ApiModelProperty(value = "供应商电子邮箱")
    @Excel(name = "供应商电子邮箱", width = 30)
    private String supplierEmail;

    @ApiModelProperty(value = "供应商主营业务")
    @Excel(name = "供应商主营业务", width = 40)
    private String supplierBusiness;

    @ApiModelProperty(value = "与供应商曾是否合作   1为未合作   0为合作",example = "0")
    @Excel(name = "与供应商曾是否合作",replace = {"曾合作_true", "未合作_false"})
    private Boolean supplierCooperated;

    @ApiModelProperty(value = "供应商   0为删除  1为未删除",example = "0")
    @Excel(name = "供应商状态",replace = {"正常_true", "删除_false"})
    private Boolean supplierStatus;

    @ApiModelProperty(value = "预留商铺id",example = "1")
    @Excel(name = "预留商铺id")
    private Long shopId;

    @ApiModelProperty(value = "所欠供应商货款")
    private Double supplierArrears;

}
