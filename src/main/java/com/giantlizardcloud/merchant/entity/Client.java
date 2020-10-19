package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_client")
@ApiModel(value="Client对象", description="")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
