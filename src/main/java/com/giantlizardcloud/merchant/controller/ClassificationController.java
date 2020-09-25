package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.entity.Classification;
import com.giantlizardcloud.merchant.service.IClassificationService;
import com.giantlizardcloud.merchant.vo.ClassificationVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/classification")
@Api(value = "商品分类管理",tags = "商品分类对应操作")
public class ClassificationController {

    private final IClassificationService classificationService;

    public ClassificationController(IClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @GetMapping("/{page}/{size}")
    public JSONResult getAll(@PathVariable Integer page,@PathVariable Integer size){
        return JSONResult.ok(classificationService.getClassificationByPage(page, size));
    }

    @GetMapping("/{likeName}")
    public JSONResult getClassificationByName(@PathVariable String likeName){
        List<Classification> list = classificationService.list(new QueryWrapper<Classification>()
                .like("classification_name", likeName).or().eq("classification_code", likeName));
        return JSONResult.ok(list);
    }

    @PostMapping
    public JSONResult addClassification(Classification classification){
        classificationService.save(classification);
        return JSONResult.ok("新增分类完成");
    }

    @PutMapping
    public JSONResult updateClassification(Classification classification){
        classificationService.update(new UpdateWrapper<Classification>()
                .set(!"".equals(classification.getClassificationName())&&classification.getClassificationName()!=null,"classification_name",classification.getClassificationName())
                .set(!"".equals(classification.getClassificationCode())&&classification.getClassificationCode()!=null,"classification_code",classification.getClassificationCode())
                .set(classification.getParentId()!=null&&classification.getParentId()!=0,"parent_id",classification.getParentId())
                .eq("classification_id",classification.getClassificationId()));
        return JSONResult.ok("修改分类完成");
    }



}
