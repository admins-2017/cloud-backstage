package com.giantlizardcloud.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FindCommodityByConditionDto {

    @ApiModelProperty(value = "商品id",example = "1")
    private Integer commodityId;

    @ApiModelProperty(value = "商品名")
    private String commodityName;

    @ApiModelProperty(value = "商品编码")
    private String commodityNumber;

    @ApiModelProperty(value = "商品单位(包，份)")
    private String commodityUnit;

    @ApiModelProperty(value = "商品状态 0为正常 1为下架 2为删除",example = "1")
    private Integer commodityStatus;

    @ApiModelProperty(value = "商品分类id",example = "1")
    private Long classificationId;

    @ApiModelProperty(value = "商品添加时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "商品添加人员",example = "1")
    private Long insertUser;

    @ApiModelProperty(value = "商品修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "商品修改人员",example = "1")
    private Long updateUser;

}
