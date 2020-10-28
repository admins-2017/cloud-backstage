package com.giantlizardcloud.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueryOrderByConditionDto {

    @ApiModelProperty(value = "销售单号")
    private String orderNumber;

    @ApiModelProperty(value = "销售单时间")
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "销售客户id")
    private Long clientId;

    @ApiModelProperty(value = "销售单未付款金额")
    private Double orderUnpaidAmount;

    @ApiModelProperty(value = "销售单结算方式(1 现金, 2  转账, 3 微信, 4 支付宝, 5 对公)",example = "1")
    private Integer orderSettlementMethod;

    @ApiModelProperty(value = "销售单状态(1 销售 2 退货 3 作废 )")
    private Integer orderStatus;

    @ApiModelProperty(value = "销售单操作用户",example = "1")
    private Long orderOperatorUser;

    @ApiModelProperty(value = "销售单操作时间")
    private LocalDateTime orderInsertTime;

    @ApiModelProperty(value = "销售单添加用户")
    private Long insertUser;

    @ApiModelProperty(value = "页码")
    private Integer page;

    @ApiModelProperty(value = "条数")
    private Integer size;
}
