package com.giantlizardcloud.merchant.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Data
public class QueryRepaymentDto {

    @ApiModelProperty(value = "还款单单号")
    private String repaymentNumber;

    @ApiModelProperty(value = "供应商id",example = "1")
    private Long supplierId;

    @ApiModelProperty(value = "还款单状态(1 正常 2 作废)",example = "1")
    private Integer repaymentStatus;

    @ApiModelProperty(value = "还款单还款方式",example = "1")
    private Integer repaymentMethod;

    @ApiModelProperty(value = "还款开始时间")
    private String repaymentStartDate;

    @ApiModelProperty(value = "还款结束时间")
    private String repaymentEndDate;

    @ApiModelProperty(value = "页码",example = "1")
    private Integer page;

    @ApiModelProperty(value = "条数",example = "1")
    private Integer size;
}
