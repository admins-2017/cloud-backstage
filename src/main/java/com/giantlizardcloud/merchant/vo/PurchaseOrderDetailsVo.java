package com.giantlizardcloud.merchant.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kang
 * @date 2020-11-02
 * @Description 采购明细单返回视图
 */
@Data
public class PurchaseOrderDetailsVo {

    @ApiModelProperty(value = "采购详情单id",example = "1")
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

    @ApiModelProperty(value = "商品单价")
    private BigDecimal commodityUnitPrice;

    @ApiModelProperty(value = "商品名")
    private String commodityName;

    @ApiModelProperty(value = "商品编码")
    private String commodityNumber;

    @ApiModelProperty(value = "商品单位(包，份)")
    private String commodityUnit;

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    private String shopAddress;


}
