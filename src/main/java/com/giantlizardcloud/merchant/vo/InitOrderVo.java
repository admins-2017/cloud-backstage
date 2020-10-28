package com.giantlizardcloud.merchant.vo;

import com.giantlizardcloud.merchant.entity.Client;
import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.sys.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitOrderVo {

    private String orderNumber;

    private List<Client> clients = new ArrayList<>();

    private List<Shop> shops = new ArrayList<>();

    private List<User> users = new ArrayList<>();

}
