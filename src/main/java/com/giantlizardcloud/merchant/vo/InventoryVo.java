package com.giantlizardcloud.merchant.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

/**
 * @author kang
 */
@Data
public class InventoryVo {
    /**
     *仓库商品数
     */
    private Integer commodityNumber;
    /**
     *仓库库存为0
     */
    private Integer stockNumber;
    /**
     *仓库警告
     */
    private Integer warnNumber;
    /**
     *仓库存量
     */
    private Integer inventoryNumber;

    private IPage<CommodityWithShopVo> vos;
}
