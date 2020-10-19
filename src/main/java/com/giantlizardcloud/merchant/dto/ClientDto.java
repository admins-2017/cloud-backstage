package com.giantlizardcloud.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="添加客户对象", description="")
public class ClientDto {

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
