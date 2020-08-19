package com.giantlizardcloud.config.aspect;

import lombok.Data;

@Data
public class IpResultEntity {

    private String resultcode;

    private String reason;

    private String error_code;

    private IpJsonEntity result;
}
