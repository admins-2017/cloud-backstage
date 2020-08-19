package com.giantlizardcloud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_details")
@ApiModel(value="UserDetails对象", description="")
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_details_id", type = IdType.AUTO)
    private Integer userDetailsId;

    @ApiModelProperty(value = "用户头像")
    private String userDetailsUrl;

    @ApiModelProperty(value = "用户性别 1 男 2女")
    private Integer userDetailsSex;

    @ApiModelProperty(value = "用户住址")
    private String userDetailsAddr;

    @ApiModelProperty(value = "用户邮箱")
    private String userDetailsMail;

    @ApiModelProperty(value = "用户联系方式")
    private String userDetailsTel;

    @ApiModelProperty(value = "商铺id")
    private Integer shopId;

    @ApiModelProperty(value = "对应user-id")
    private Long userId;


}
