package com.giantlizardcloud.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_shop")
@ApiModel(value="Shop对象", description="")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long shopId;

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    private String shopAddress;

    @ApiModelProperty(value = "1:正常 2:休息 3:作废")
    private Integer shopStatus;

    private LocalDateTime insertTime;

    private Long insertUser;

    private LocalDateTime updateTime;

    private Long updateUser;

    @ApiModelProperty(value = "租户标示")
    private Long tenantId;


}
