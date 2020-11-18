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
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_purchase_order_details")
@ApiModel(value="PurchaseOrderDetails对象", description="采购单明细实体类")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "采购详情单id",example = "1")
    @TableId(value = "purchase_details_id", type = IdType.AUTO)
    private Long purchaseDetailsId;

    @ApiModelProperty(value = "采购单id",example = "1")
    private Long purchaseId;

    @ApiModelProperty(value = "商品id",example = "1")
    private Integer commodityId;

    @ApiModelProperty(value = "商铺id",example = "1")
    private Long shopId;

    @ApiModelProperty(value = "采购数量",example = "1")
    private Integer purchaseDetailNumber;

    @ApiModelProperty(value = "采购详情价格")
    private BigDecimal purchaseDetailPrice;

    public PurchaseOrderDetails(Integer commodityId, Long shopId, Integer purchaseDetailNumber, BigDecimal purchaseDetailPrice) {
        this.commodityId = commodityId;
        this.shopId = shopId;
        this.purchaseDetailNumber = purchaseDetailNumber;
        this.purchaseDetailPrice = purchaseDetailPrice;
    }
}
