package com.giantlizardcloud.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Administrator
 */
@Data
public class AddRepaymentDto {

    @ApiModelProperty(value = "还款单单号")
    private String repaymentNumber;

    @ApiModelProperty(value = "供应商id",example = "1")
    private Long supplierId;

    @ApiModelProperty(value = "还款单还款金额",example = "1")
    private Double repaymentSum;

    @ApiModelProperty(value = "还款单还款方式",example = "1")
    private Integer repaymentMethod;

    @ApiModelProperty(value = "还款单还款账户")
    private String repaymentAccountNumber;

    @ApiModelProperty(value = "还款时间")
    private LocalDateTime repaymentDate;

    @ApiModelProperty(value = "还款单备注")
    private String repaymentRemark;

    private List<String> children;
}
