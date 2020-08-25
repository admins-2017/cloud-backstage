package com.giantlizardcloud.dto;

import lombok.Data;

@Data
public class UpdateUserDto {

    private Long userId;

    private Integer userDetailsSex;

    private String userDetailsAddr;

    private String userDetailsMail;

    private Long shopId;

    private Long roleId;
}
