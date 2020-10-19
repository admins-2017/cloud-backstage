package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

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
 * @since 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_client")
@ApiModel(value="Client对象", description="")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户ID")
    @TableId(value = "client_id")
    private Long clientId;

    @ApiModelProperty(value = "名字")
    private String clientName;

    @ApiModelProperty(value = "电话号")
    private String clientPhone;

    @ApiModelProperty(value = "客户地址")
    private String clientAddress;

    @ApiModelProperty(value = "性别")
    private Boolean clientGender;

    @ApiModelProperty(value = "生日")
    private LocalDateTime clientBirthday;

    @ApiModelProperty(value = "客户状态 0 为正常 1为删除")
    private Integer clientStatus;

    @ApiModelProperty(value = "客户欠款")
    private Double clientConsumption;

    @ApiModelProperty(value = "客户邮箱")
    private String clientEmail;

    @ApiModelProperty(value = "客户简介")
    private String clientDesc;

    @ApiModelProperty(value = "商铺Id")
    private Long shopId;


}
