package com.giantlizardcloud.merchant.enums;

import lombok.Getter;

/**
 * @author kang
 * 采购单状态枚举类
 */

@Getter
public enum PurchaseStatusEnum {
    /**
     * 采购进货状态
     */
    SAVE(1, "进货"),
    /**
     * 采购退货状态
     */
    RETURN(2, "退回"),

    INVALID(3,"作废"),
    ;
    private Integer code;

    private String message;

    PurchaseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
