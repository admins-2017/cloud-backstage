package com.giantlizardcloud.merchant.vo;

import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.merchant.entity.Supplier;
import com.giantlizardcloud.sys.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitPurchaseOrderVo {

    private String orderNumber;

    private List<Supplier> suppliers = new ArrayList<>();

    private List<Shop> shops = new ArrayList<>();

    private List<User> users = new ArrayList<>();
}
