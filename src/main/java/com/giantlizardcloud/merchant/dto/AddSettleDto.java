package com.giantlizardcloud.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSettleDto {

    @ApiModelProperty(value = "结清单单号")
    private String settleNumber;

    @ApiModelProperty(value = "结清客户id",example = "1")
    private Long clientId;

    @ApiModelProperty(value = "结清金额",example = "1")
    private Double settleSum;

    @ApiModelProperty(value = "结清方式",example = "1")
    private Integer settleMethod;

    @ApiModelProperty(value = "结清账户")
    private String settleAccountNumber;

    @ApiModelProperty(value = "结清时间")
    private LocalDateTime settleDate;

    @ApiModelProperty(value = "结清备注")
    private String settleRemark;

    @ApiModelProperty(value = "结清备注")
    private List<String> urls;
}
