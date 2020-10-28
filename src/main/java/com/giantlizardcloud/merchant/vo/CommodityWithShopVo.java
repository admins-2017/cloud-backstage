package com.giantlizardcloud.merchant.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kang
 */
@Data
public class CommodityWithShopVo {

    @Excel(name = "仓库id",  isImportField = "true_st")
    private Integer inventoryId;

    @ApiModelProperty(value = "库存数量")
    @Excel(name = "库存数量",  isImportField = "true_st")
    private Integer inventoryNumber;

    @ApiModelProperty(value = "商铺id",example = "1")
    private Long shopId;

    @ApiModelProperty(value = "商铺名称")
    @Excel(name = "商铺名称", width = 30, isImportField = "true_st")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    @Excel(name = "商铺地址",width = 30,  isImportField = "true_st")
    private String shopAddress;

    @ApiModelProperty(value = "商品id",example = "1")
    private Integer commodityId;

    @ApiModelProperty(value = "商品名")
    @Excel(name = "商品名", width = 30,   isImportField = "true_st")
    private String commodityName;

    @ApiModelProperty(value = "商品编码")
    @Excel(name = "商品编码", width = 30,  isImportField = "true_st")
    private String commodityNumber;

    @ApiModelProperty(value = "商品图片")
    private String commodityPicture;

    @ApiModelProperty(value = "建议售价")
    @Excel(name = "建议售价",  isImportField = "true_st")
    private BigDecimal commoditySellingPrice;

    @ApiModelProperty(value = "商品单位(包，份)")
    @Excel(name = "商品单位(包，份)", isImportField = "true_st")
    private String commodityUnit;

    @ApiModelProperty(value = "商品状态 0为正常 1为下架 2为删除",example = "1")
    @Excel(name = "商品状态",replace = {"上架_0", "下架_1","删除_2"},  isImportField = "true_st")
    private Integer commodityStatus;

    @ApiModelProperty(value = "商品描述")
    @Excel(name = "商品描述",width = 70,   isImportField = "true_st")
    private String commodityDescription;

    @ApiModelProperty(value = "商品分类id",example = "1")
    private Long classificationId;

    @ApiModelProperty(value = "商品分类名称")
    @Excel(name = "商品分类名称", isImportField = "true_st")
    private String classificationName;
}
