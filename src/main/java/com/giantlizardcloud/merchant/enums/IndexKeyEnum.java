package com.giantlizardcloud.merchant.enums;

import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
public enum  IndexKeyEnum {
    /**
     * 销量排行key
     */
    SALE("sales-ranking"),
    /**
     * 统计key
     */
    STATISTICS("statistics");
            ;

    private String message;

    IndexKeyEnum(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
