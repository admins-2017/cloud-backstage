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
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_settle_annex")
@ApiModel(value="SettleAnnex对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class SettleAnnex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "附件id")
    @TableId(value = "annex_id", type = IdType.AUTO)
    private Long annexId;

    @ApiModelProperty(value = "附件地址")
    private String annexUrl;

    @ApiModelProperty(value = "对应结清单id")
    private Long settleId;

    public SettleAnnex(String annexUrl, Long settleId) {
        this.annexUrl = annexUrl;
        this.settleId = settleId;
    }
}
