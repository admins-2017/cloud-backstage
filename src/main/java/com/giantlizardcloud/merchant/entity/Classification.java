package com.giantlizardcloud.merchant.entity;

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
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchant_classification")
@ApiModel(value="Classification对象", description="")
public class Classification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品分类主键id",example = "1")
    @TableId(value = "classification_id", type = IdType.AUTO)
    private Long classificationId;

    @ApiModelProperty(value = "商品分类编码")
    private String classificationCode;

    @ApiModelProperty(value = "商品分类名称")
    private String classificationName;

    @ApiModelProperty(value = "分类父类id 0为一级分类",example = "1")
    private Long parentId;


}
