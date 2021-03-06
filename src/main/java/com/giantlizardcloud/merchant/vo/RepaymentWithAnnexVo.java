package com.giantlizardcloud.merchant.vo;

import com.giantlizardcloud.merchant.entity.RepaymentAnnex;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Administrator
 */
@Data
public class RepaymentWithAnnexVo {

    private Long repaymentId;

    @ApiModelProperty(value = "还款单单号")
    private String repaymentNumber;

    @ApiModelProperty(value = "供应商id",example = "1")
    private Long supplierId;

    @ApiModelProperty(value = "供应商名")
    private String supplierName;

    @ApiModelProperty(value = "还款单还款金额",example = "1")
    private Double repaymentSum;

    @ApiModelProperty(value = "还款单状态(1 正常 2 作废)",example = "1")
    private Integer repaymentStatus;

    @ApiModelProperty(value = "还款单还款方式",example = "1")
    private Integer repaymentMethod;

    @ApiModelProperty(value = "还款单还款账户")
    private String repaymentAccountNumber;

    @ApiModelProperty(value = "还款时间")
    private LocalDateTime repaymentDate;

    @ApiModelProperty(value = "还款单备注")
    private String repaymentRemark;

    @ApiModelProperty(value = "还款单添加人员",example = "1")
    private Long insertUser;

    @ApiModelProperty(value = "还款单添加人员名称")
    private String insertUserName;

    @ApiModelProperty(value = "还款单添加时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "还款单作废人员",example = "1")
    private Long repaymentInvalidUser;

    @ApiModelProperty(value = "还款单作废人员名称")
    private String invalidUserName;

    @ApiModelProperty(value = "还款单作废时间")
    private LocalDateTime repaymentInvalidTime;

    @ApiModelProperty(value = "还款附件列表")
    private List<RepaymentAnnex> children;
}
