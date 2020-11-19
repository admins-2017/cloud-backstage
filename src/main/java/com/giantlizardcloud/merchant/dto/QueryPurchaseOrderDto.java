package com.giantlizardcloud.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Administrator
 * @Description 采购订单查询条件
 */
@Data
public class QueryPurchaseOrderDto {

    @ApiModelProperty(value = "采购订单单号")
    private String number;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "供应商id",example = "1")
    private Long supplier;

    @ApiModelProperty(value = "采购人员",example = "1")
    private Long user;

    @ApiModelProperty(value = "页码",example = "1")
    private Integer page;

    @ApiModelProperty(value = "条数",example = "1")
    private Integer size;
}
