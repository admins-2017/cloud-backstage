package com.giantlizardcloud.config.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginErrorVo {

    private Integer status;

    private String message;
}
