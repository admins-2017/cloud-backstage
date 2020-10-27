package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_order")
@ApiModel(value="Order对象", description="order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "销售单id",example = "1")
    private Long orderId;

    @ApiModelProperty(value = "销售单号")
    private String orderNumber;

    @ApiModelProperty(value = "销售单时间")
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "销售客户id")
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

    @ApiModelProperty(value = "销售单状态(1 销售 2 退货 3 作废 )")
    private Integer orderStatus;

    @ApiModelProperty(value = "销售单操作用户",example = "1")
    @TableField(fill = FieldFill.INSERT)
    private Long orderOperatorUser;

    @ApiModelProperty(value = "销售单操作时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime orderInsertTime;


}
