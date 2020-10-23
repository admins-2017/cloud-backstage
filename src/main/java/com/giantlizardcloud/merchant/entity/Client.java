package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ApiModelProperty(value = "客户ID",example = "1")
    @TableId(value = "client_id")
    private Long clientId;

    @ApiModelProperty(value = "名字")
    private String clientName;

    @ApiModelProperty(value = "电话号")
    private String clientPhone;

    @ApiModelProperty(value = "客户地址")
    private String clientAddress;

    @ApiModelProperty(value = "性别 false 男  true 女",example = "1")
    private Boolean clientGender;

    @ApiModelProperty(value = "生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate clientBirthday;

    @ApiModelProperty(value = "客户状态 0 为删除  1为正常",example = "0")
    private Boolean clientStatus;

    @ApiModelProperty(value = "客户欠款",example = "1")
    private Double clientConsumption;

    @ApiModelProperty(value = "客户邮箱")
    private String clientEmail;

    @ApiModelProperty(value = "客户简介")
    private String clientDesc;

    @ApiModelProperty(value = "商铺Id",example = "1")
    private Long shopId;

}
