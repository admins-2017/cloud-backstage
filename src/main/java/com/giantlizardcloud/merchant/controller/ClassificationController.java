package com.giantlizardcloud.merchant.controller;


import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.service.IClassificationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
        return JSONResult.ok();
    }
}
