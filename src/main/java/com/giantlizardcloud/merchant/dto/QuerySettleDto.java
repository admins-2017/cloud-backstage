package com.giantlizardcloud.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuerySettleDto {

    @ApiModelProperty(value = "结清单单号")
    private String settleNumber;

    @ApiModelProperty(value = "结清客户id")
    private Long clientId;

    @ApiModelProperty(value = "结清单状态(1 结清 2 作废)")
    private Integer settleStatus;

    @ApiModelProperty(value = "结清方式")
    private Integer settleMethod;

    @ApiModelProperty(value = "结清开始时间")
    private String settleStartDate;

    @ApiModelProperty(value = "结清结束时间")
    private String settleEndDate;

    @ApiModelProperty(value = "页码")
    private Integer page;

    @ApiModelProperty(value = "条数")
    private Integer size;
}
