package com.giantlizardcloud.merchant.entity;

import java.math.BigDecimal;
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
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_statistics")
@ApiModel(value="Statistics对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收支统计主键")
    @TableId(value = "statistics_id", type = IdType.AUTO)
    private Integer statisticsId;

    @ApiModelProperty(value = "收支统计年")
    private Integer statisticsYear;

    @ApiModelProperty(value = "收支统计月")
    private Integer statisticsMonth;

    @ApiModelProperty(value = "收支统计销售金额")
    private Double statisticsSale;

    @ApiModelProperty(value = "收支统计支出金额")
    private Double statisticsPurchase;

    public Statistics(Integer statisticsYear, Integer statisticsMonth, Double statisticsSale, Double statisticsPurchase) {
        this.statisticsYear = statisticsYear;
        this.statisticsMonth = statisticsMonth;
        this.statisticsSale = statisticsSale;
        this.statisticsPurchase = statisticsPurchase;
    }
}
