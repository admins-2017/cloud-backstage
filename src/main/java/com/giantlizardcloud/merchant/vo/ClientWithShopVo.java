package com.giantlizardcloud.merchant.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientWithShopVo {
    @ApiModelProperty(value = "客户ID")
    @TableId(value = "client_id", type = IdType.AUTO)
    private Long clientId;

    @ApiModelProperty(value = "名字")
    private String clientName;

    @ApiModelProperty(value = "电话号")
    private String clientPhone;

    @ApiModelProperty(value = "客户地址")
    private String clientAddress;

    @ApiModelProperty(value = "性别")
    private Boolean clientGender;

    @ApiModelProperty(value = "身份证")
    private String clientIdntity;

    @ApiModelProperty(value = "消费次数")
    private Integer clientNumbercsp;

    @ApiModelProperty(value = "消费金额")
    private Double clientConsumption;

    @ApiModelProperty(value = "持卡类型")
    private String clientCardtype;

    private String clientDest;

    @ApiModelProperty(value = "商品Id")
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

    @ApiModelProperty(value = "新增商铺的用户",example = "1")
    @TableField(fill = FieldFill.INSERT)
    private Long insertUser;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新商铺的用户",example = "1")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;

    @ApiModelProperty(value = "商铺联系电话")
    private String shopTel;

    @ApiModelProperty(value = "商铺介绍/公告")
    private String shopIntroduction;

    @ApiModelProperty(value = "租户标示",example = "1")
    private Long tenantId;

}
