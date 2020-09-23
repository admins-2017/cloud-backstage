package com.giantlizardcloud.merchant.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.giantlizardcloud.vo.MenuTreeVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassificationVo {

    @ApiModelProperty(value = "商品分类主键id",example = "1")
    private Long classificationId;

    @ApiModelProperty(value = "商品分类编码")
    private String classificationCode;

    @ApiModelProperty(value = "商品分类名称")
    private String classificationName;

    @ApiModelProperty(value = "分类父类id 0为一级分类",example = "1")
    private Long parentId;

    private List<ClassificationVo> children = new ArrayList<ClassificationVo>();

}
