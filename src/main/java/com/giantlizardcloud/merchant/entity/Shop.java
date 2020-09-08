package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_shop")
@ApiModel(value="Shop对象", description="")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商铺id")
    private Long shopId;

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    private String shopAddress;

    @ApiModelProperty(value = "1:正常 2:休息 3:作废",example = "1")
    private Integer shopStatus;

    @ApiModelProperty(value = "新增时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "新增商铺的用户")
    @TableField(fill = FieldFill.INSERT)
    private Long insertUser;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新商铺的用户")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;

    @ApiModelProperty(value = "商铺联系电话")
    private String shopTel;

    @ApiModelProperty(value = "商铺介绍/公告")
    private String shopIntroduction;

    @ApiModelProperty(value = "租户标示")
    private Long tenantId;


}
