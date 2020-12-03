package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_day")
@ApiModel(value="Day对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Day implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "day_id", type = IdType.AUTO)
    private Integer dayId;

    @ApiModelProperty(value = "每日统计年份")
    private Integer dayYear;

    @ApiModelProperty(value = "每日统计月份")
    private Integer dayMonth;

    @ApiModelProperty(value = "每日统计日期")
    private Integer dayDate;

    @ApiModelProperty(value = "销售次数")
    private Integer saleNumber;

    @ApiModelProperty(value = "进货次数")
    private Integer purchaseNumber;

    @ApiModelProperty(value = "销售退货次数")
    private Integer returnSaleNumber;

    @ApiModelProperty(value = "进货退货次数")
    private Integer returnPurchaseNumber;

    public Day(Integer dayYear, Integer dayMonth, Integer dayDate,
               Integer saleNumber, Integer purchaseNumber, Integer returnSaleNumber,
               Integer returnPurchaseNumber) {
        this.dayYear = dayYear;
        this.dayMonth = dayMonth;
        this.dayDate = dayDate;
        this.saleNumber = saleNumber;
        this.purchaseNumber = purchaseNumber;
        this.returnSaleNumber = returnSaleNumber;
        this.returnPurchaseNumber = returnPurchaseNumber;
    }
}
