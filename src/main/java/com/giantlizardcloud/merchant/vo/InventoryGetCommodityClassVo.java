package com.giantlizardcloud.merchant.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryGetCommodityClassVo {
    @Excel(name = "商品ID", isImportField = "true_st")
    private Integer commodityId;

    @ApiModelProperty(value = "商品名")
    @Excel(name = "商品名", width = 30,  isImportField = "true_st")
    private String commodityName;

    @ApiModelProperty(value = "商品编码")
    @Excel(name = "商品编码", width = 20, isImportField = "true_st")
    private String commodityNumber;

    @ApiModelProperty(value = "建议售价")
    @Excel(name = "建议售价", isImportField = "true_st")
    private BigDecimal commoditySellingPrice;

    @ApiModelProperty(value = "商品单位(包，份)")
    @Excel(name = "商品单位(包，份)", width = 20, isImportField = "true_st")
    private String commodityUnit;

    @ApiModelProperty(value = "商品描述")
    @Excel(name = "商品描述", width = 70,  isImportField = "true_st")
    private String commodityDescription;

    @ApiModelProperty(value = "商品分类名称")
    @Excel(name = "商品分类名称",  isImportField = "true_st")
    private String classificationName;
}
