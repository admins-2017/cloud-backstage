package com.giantlizardcloud.dto;

import lombok.Data;

@Data
public class VerifyCodeDto {

    private String uuid;

    private Integer result;

    private String phoneNumber;
}
