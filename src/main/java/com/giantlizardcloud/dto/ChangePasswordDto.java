package com.giantlizardcloud.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {

    private String phoneNumber;

    private String password;
}
