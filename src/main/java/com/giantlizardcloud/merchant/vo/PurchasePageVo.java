package com.giantlizardcloud.merchant.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kang
 * @Description 采购单分页视图
 */
@Data
public class PurchasePageVo {

    @ApiModelProperty(value = "采购订单id", example = "1")
    private Long purchaseId;

    @ApiModelProperty(value = "采购订单单号")
    private String purchaseNumber;

    @ApiModelProperty(value = "采购时间")
    private LocalDateTime purchaseDate;

    @ApiModelProperty(value = "供应商id", example = "1")
    private Long supplierId;

    @ApiModelProperty(value = "采购订单总金额")
    private Double purchaseTotalAmount;

    @ApiModelProperty(value = "采购订单折扣率")
    private Double purchaseDiscountRate;

    @ApiModelProperty(value = "采购订单折后金额")
    private Double purchaseAmountAfterDiscount;

    @ApiModelProperty(value = "采购订单已结金额")
    private Double purchaseActualPayment;

    @ApiModelProperty(value = "采购订单未结金额")
    private Double purchaseUnpaidAmount;

    @ApiModelProperty(value = "采购订单支付方式", example = "1")
    private Integer purchaseSettlementMethod;

    @ApiModelProperty(value = "采购订单支付账号")
    private String purchaseSettlementAccount;

    @ApiModelProperty(value = "采购订单备注")
    private String purchaseRemarks;

    @ApiModelProperty(value = "采购订单状态(1- 进货 2-退货 3-作废)", example = "1")
    private Integer purchaseStatus;

    @ApiModelProperty(value = "采购人员", example = "1")
    private Long purchaseOperatorUser;

    @ApiModelProperty(value = "采购订单添加时间")
    private LocalDateTime purchaseInsertTime;

    @ApiModelProperty(value = "采购顶单添加人员", example = "1")
    private Long insertUser;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "采购人员名称")
    private String operatorUserName;

    @ApiModelProperty(value = "采购顶单添加人员名称", example = "1")
    private String insertUserName;

    private List<PurchaseOrderDetailsVo> children;


}
