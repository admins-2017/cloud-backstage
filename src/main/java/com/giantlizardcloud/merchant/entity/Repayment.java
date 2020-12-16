package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_repayment")
@ApiModel(value="Repayment对象", description="还款单实体类")
public class Repayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "还款单id")
    @TableId(value = "repayment_id", type = IdType.AUTO)
    private Long repaymentId;

    @ApiModelProperty(value = "还款单单号")
    private String repaymentNumber;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "还款单还款金额")
    private Double repaymentSum;

    @ApiModelProperty(value = "还款单状态(1 正常 2 作废)")
    private Boolean repaymentStatus;

    @ApiModelProperty(value = "还款单还款方式")
    private Integer repaymentMethod;

    @ApiModelProperty(value = "还款单还款账户")
    private String repaymentAccountNumber;

    @ApiModelProperty(value = "还款时间")
    private LocalDateTime repaymentDate;

    @ApiModelProperty(value = "还款单备注")
    private String repaymentRemark;

    @ApiModelProperty(value = "还款单添加人员")
    private Long insertUser;

    @ApiModelProperty(value = "还款单添加时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "还款单作废人员")
    private Long repaymentInvalidUser;

    @ApiModelProperty(value = "还款单作废时间")
    private LocalDateTime repaymentInvalidTime;


}
