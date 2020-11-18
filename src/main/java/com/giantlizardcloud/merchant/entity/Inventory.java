package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_inventory")
@ApiModel(value="Inventory对象", description="")
@NoArgsConstructor
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "inventory_id", type = IdType.AUTO)
    private Integer inventoryId;

    @ApiModelProperty(value = "商品id")
    private Integer commodityId;

    @ApiModelProperty(value = "商铺id")
    private Long shopId;

    @ApiModelProperty(value = "库存数量")
    private Integer inventoryNumber;

    public Inventory(Integer commodityId, Long shopId, Integer inventoryNumber) {
        this.commodityId = commodityId;
        this.shopId = shopId;
        this.inventoryNumber = inventoryNumber;
    }
}
