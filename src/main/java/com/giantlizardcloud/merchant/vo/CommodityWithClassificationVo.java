package com.giantlizardcloud.merchant.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityWithClassificationVo {
    @ApiModelProperty(value = "商品id",example = "1")
    private Integer commodityId;

    @ApiModelProperty(value = "商品名")
    private String commodityName;

    @ApiModelProperty(value = "商品编码")
    private String commodityNumber;

    @ApiModelProperty(value = "商品图片")
    private String commodityPicture;

    @ApiModelProperty(value = "建议售价")
    private BigDecimal commoditySellingPrice;

    @ApiModelProperty(value = "商品单位(包，份)")
    private String commodityUnit;

    @ApiModelProperty(value = "商品状态 0为正常 1为下架 2为删除",example = "1")
    private Integer commodityStatus;

    @ApiModelProperty(value = "商品描述")
    private String commodityDescription;

    @ApiModelProperty(value = "商品分类id",example = "1")
    private Long classificationId;

    @ApiModelProperty(value = "商品添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "商品添加人员",example = "1")
    private Long insertUser;

    @ApiModelProperty(value = "添加商品人员名称")
    private String insertUsername;

    @ApiModelProperty(value = "商品修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "商品修改人员",example = "1")
    private Long updateUser;

    @ApiModelProperty(value = "修改商品人员名称")
    private String updateUsername;

    @ApiModelProperty(value = "商品分类编码")
    private String classificationCode;

    @ApiModelProperty(value = "商品分类名称")
    private String classificationName;
}
