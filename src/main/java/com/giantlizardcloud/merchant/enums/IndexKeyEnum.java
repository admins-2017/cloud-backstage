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
    STATISTICS("statistics"),
    /**
     * 销售次数统计
     */
    SALE_COUNT("saleCount"),
    /**
     * 进货次数统计
     */
    PURCHASE_COUNT("purchaseCount"),
    /**
     * 统计key
     */
    SALE_AMOUNT("saleAmount"),
    /**
     * 销售value - name
     */
    AMOUNT_OF_PAYOUT("amountOfPayout");
            ;

    private String message;

    IndexKeyEnum(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
