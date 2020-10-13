package com.giantlizardcloud.merchant.dto;

import lombok.Data;

@Data
public class QueryInventory {

    private Long shopId ;

    private Integer page;

    private Integer size;
}
