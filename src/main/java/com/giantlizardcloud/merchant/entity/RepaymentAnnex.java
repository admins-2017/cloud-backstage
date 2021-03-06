package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_repayment_annex")
@ApiModel(value="RepaymentAnnex对象", description="")
@NoArgsConstructor
public class RepaymentAnnex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "附件id")
    @TableId(type = IdType.AUTO)
    private Long annexId;

    @ApiModelProperty(value = "附件地址")
    private String annexUrl;

    @ApiModelProperty(value = "对应还款单id")
    private Long repaymentId;

    public RepaymentAnnex(String annexUrl, Long repaymentId) {
        this.annexUrl = annexUrl;
        this.repaymentId = repaymentId;
    }
}
