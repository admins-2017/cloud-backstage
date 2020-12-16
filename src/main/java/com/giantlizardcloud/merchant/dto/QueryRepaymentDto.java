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

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "还款单状态(1 正常 2 作废)")
    private Boolean repaymentStatus;

    @ApiModelProperty(value = "还款单还款方式")
    private Integer repaymentMethod;

    @ApiModelProperty(value = "还款开始时间")
    private LocalDateTime repaymentStartDate;

    @ApiModelProperty(value = "还款结束时间")
    private LocalDateTime repaymentEndDate;

    @ApiModelProperty(value = "页码")
    private Integer page;

    @ApiModelProperty(value = "条数")
    private Integer size;
}
