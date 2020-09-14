package com.giantlizardcloud.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShopWithUserVo {
    @ApiModelProperty(value = "商铺id",example = "1")
    private Long shopId;

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    private String shopAddress;

    @ApiModelProperty(value = "1:正常 2:休息 3:作废",example = "1")
    private Integer shopStatus;

    @ApiModelProperty(value = "新增时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "新增商铺的用户",example = "1")
    private Long insertUser;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新商铺的用户",example = "1")
    private Long updateUser;

    @ApiModelProperty(value = "商铺联系电话")
    private String shopTel;

    @ApiModelProperty(value = "商铺介绍/公告")
    private String shopIntroduction;

    private List<UserDetailsVo> children= new ArrayList<>();
}
