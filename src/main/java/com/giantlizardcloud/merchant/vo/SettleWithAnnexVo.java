package com.giantlizardcloud.merchant.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Administrator
 */
@Data
public class SettleWithAnnexVo {

    @ApiModelProperty(value = "结清证明id",example = "1")
    private Long settleId;

    @ApiModelProperty(value = "结清单单号")
    private String settleNumber;

    @ApiModelProperty(value = "结清客户id",example = "1")
    private Long clientId;

    @ApiModelProperty(value = "客户名称")
    private String clientName;

    @ApiModelProperty(value = "结清金额",example = "1")
    private Double settleSum;

    @ApiModelProperty(value = "结清单状态(1 结清 2 作废)",example = "1")
    private Integer settleStatus;

    @ApiModelProperty(value = "结清方式",example = "1")
    private Integer settleMethod;

    @ApiModelProperty(value = "结清账户")
    private String settleAccountNumber;

    @ApiModelProperty(value = "结清时间")
    private LocalDateTime settleDate;

    @ApiModelProperty(value = "结清备注")
    private String settleRemark;

    @ApiModelProperty(value = "结清单添加人员",example = "1")
    private Long insertUser;

    @ApiModelProperty(value = "结清单添加人员名称")
    private String insertUserName;

    @ApiModelProperty(value = "结清单添加时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "结清单作废人员",example = "1")
    private Long settleInvalidUser;

    @ApiModelProperty(value = "结清单作废人员名称")
    private String invalidUserName;

    @ApiModelProperty(value = "结清单作废时间")
    private LocalDateTime settleInvalidTime;

    private List<String> children;
}
