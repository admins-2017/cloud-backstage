package com.giantlizardcloud.merchant.vo;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.giantlizardcloud.vo.UserDetailsVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 */
@Data
public class OrderAndClientAndUserVO {

    @ApiModelProperty(value = "销售单id",example = "1")
    private Long orderId;

    @ApiModelProperty(value = "销售单号")
    private String orderNumber;

    @ApiModelProperty(value = "销售单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "销售客户id",example = "1")
    private Long clientId;

    @ApiModelProperty(value = "销售单合计金额(总金额)")
    private Double orderTotalAmount;

    @ApiModelProperty(value = "销售单折扣率")
    private Double orderDiscountRate;

    @ApiModelProperty(value = "销售单折后金额")
    private Double orderAmountAfterDiscount;

    @ApiModelProperty(value = "销售单实付款金额")
    private Double orderActualPayment;

    @ApiModelProperty(value = "销售单未付款金额")
    private Double orderUnpaidAmount;

    @ApiModelProperty(value = "销售单结算方式(1 现金, 2  转账, 3 微信, 4 支付宝, 5 对公)",example = "1")
    private Integer orderSettlementMethod;

    @ApiModelProperty(value = "销售单结算账户(收款账户)")
    private String orderSettlementAccount;

    @ApiModelProperty(value = "销售单备注信息")
    private String orderRemarks;

    @ApiModelProperty(value = "销售单状态(1 销售 2 退货 3 作废 )",example = "1")
    private Integer orderStatus;

    @ApiModelProperty(value = "销售单操作用户",example = "1")
    private Long orderOperatorUser;

    @ApiModelProperty(value = "销售单操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime orderInsertTime;

    @ApiModelProperty(value = "名字")
    private String clientName;

    @ApiModelProperty(value = "电话号")
    private String clientPhone;

    @ApiModelProperty(value = "订单操作用户")
    private String operatorUser;

    @ApiModelProperty(value = "添加用户")
    private String insertUsername;

    private List<OrderDetailsVo> children;
}
