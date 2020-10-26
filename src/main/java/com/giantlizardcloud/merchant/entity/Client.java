package com.giantlizardcloud.merchant.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
    @Excel(name = "客户系统编号", width = 20)
    private Long clientId;

    @ApiModelProperty(value = "名字")
    @Excel(name = "客户名称", width = 30)
    private String clientName;

    @ApiModelProperty(value = "电话号")
    @Excel(name = "客户电话号", width = 30)
    private String clientPhone;

    @ApiModelProperty(value = "客户地址")
    @Excel(name = "客户地址", width = 30)
    private String clientAddress;

    @ApiModelProperty(value = "性别 false 男  true 女",example = "1")
    @Excel(name = "客户性别", replace = {"男_true","女_false"})
    private Boolean clientGender;

    @ApiModelProperty(value = "生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "客户生日", width = 15)
    private LocalDate clientBirthday;

    @ApiModelProperty(value = "客户状态 0 为删除  1为正常",example = "0")
    @Excel(name = "客户状态",replace = {"正常_true","删除_false"})
    private Boolean clientStatus;

    @ApiModelProperty(value = "客户欠款",example = "1")
    @Excel(name = "客户欠款")
    private Double clientConsumption;

    @ApiModelProperty(value = "客户邮箱")
    @Excel(name = "客户邮箱", width = 30)
    private String clientEmail;

    @ApiModelProperty(value = "客户简介")
    @Excel(name = "客户简介", width = 50)
    private String clientDesc;

    @ApiModelProperty(value = "商铺Id",example = "1")
    @Excel(name = "预留商铺Id", width = 30)
    private Long shopId;

}
