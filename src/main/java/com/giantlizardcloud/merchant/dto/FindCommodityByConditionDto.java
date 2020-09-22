package com.giantlizardcloud.merchant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @ApiModelProperty(value = "商品添加开始时间")
    private String insertStartTime;

    @ApiModelProperty(value = "商品添加结束时间")
    private String insertEndTime;

    @ApiModelProperty(value = "商品添加人员",example = "1")
    private Long insertUser;

    @ApiModelProperty(value = "商品修改时间")
    private String updateStartTime;

    @ApiModelProperty(value = "商品修改时间")
    private String updateEndTime;

    @ApiModelProperty(value = "商品修改人员",example = "1")
    private Long updateUser;

    @ApiModelProperty(value = "页码",example = "1")
    private Integer page;

    @ApiModelProperty(value = "条数",example = "1")
    private Integer size;
}
