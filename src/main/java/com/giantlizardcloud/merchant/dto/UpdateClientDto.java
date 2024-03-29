package com.giantlizardcloud.merchant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author kang
 */
@Data
public class UpdateClientDto {

    @ApiModelProperty(value = "客户ID",example = "1")
    private Long clientId;

    @ApiModelProperty(value = "名字")
    private String clientName;

    @ApiModelProperty(value = "电话号")
    private String clientPhone;

    @ApiModelProperty(value = "客户地址")
    private String clientAddress;

    @ApiModelProperty(value = "性别" ,example = "1")
    private Boolean clientGender;

    @ApiModelProperty(value = "生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate clientBirthday;

    @ApiModelProperty(value = "客户邮箱")
    private String clientEmail;

    @ApiModelProperty(value = "客户简介")
    private String clientDesc;

}
