package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_settle")
@ApiModel(value="Settle对象", description="")
public class Settle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "结清证明id")
    @TableId(value = "settle_id")
    private Long settleId;

    @ApiModelProperty(value = "结清单单号")
    private String settleNumber;

    @ApiModelProperty(value = "结清客户id")
    private Long clientId;

    @ApiModelProperty(value = "结清金额")
    private Double settleSum;

    @ApiModelProperty(value = "结清单状态(1 结清 2 作废)")
    private Boolean settleStatus;

    @ApiModelProperty(value = "结清方式")
    private Integer settleMethod;

    @ApiModelProperty(value = "结清账户")
    private String settleAccountNumber;

    @ApiModelProperty(value = "结清时间")
    private LocalDateTime settleDate;

    @ApiModelProperty(value = "结清备注")
    private String settleRemark;

    @ApiModelProperty(value = "结清单添加人员")
    private Long insertUser;

    @ApiModelProperty(value = "结清单添加时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "结清单作废人员")
    private Long settleInvalidUser;

    @ApiModelProperty(value = "结清单作废时间")
    private LocalDateTime settleInvalidTime;


}
