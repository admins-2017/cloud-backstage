package com.giantlizardcloud.merchant.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_commodity")
@ApiModel(value="Commodity对象", description="")
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "商品id",example = "1")
    @TableId(value = "commodity_id", type = IdType.AUTO)
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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "商品添加人员",example = "1")
    @TableField(fill = FieldFill.INSERT)
    private Long insertUser;

    @ApiModelProperty(value = "商品修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "商品修改人员",example = "1")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;


}
