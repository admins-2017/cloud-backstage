package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.AddCommodityDto;
import com.giantlizardcloud.merchant.dto.FindCommodityByConditionDto;
import com.giantlizardcloud.merchant.entity.Commodity;
import com.giantlizardcloud.merchant.service.ICommodityService;
import com.giantlizardcloud.merchant.vo.CommodityWithClassificationVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/commodity")
@Api(value = "商品管理",tags = "商品明细对应操作")
@Slf4j
public class CommodityController {

    private final ICommodityService commodityService;

    public CommodityController(ICommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @GetMapping("/{page}/{size}")
    public JSONResult getAllCommodity(@PathVariable Integer page ,@PathVariable Integer size){
        IPage<CommodityWithClassificationVo> commodityList = commodityService.getAllCommodityByPage(page,size);
        return JSONResult.ok(commodityList);
    }

    @GetMapping
    public JSONResult getCommodityByCondition(FindCommodityByConditionDto dto){
        log.info(dto.toString());
        IPage<CommodityWithClassificationVo> list = commodityService.getCommodityByCondition(dto);
        return JSONResult.ok(list);
    }

    @PostMapping
    public JSONResult addCommodity(AddCommodityDto dto){
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(dto,commodity);
        commodityService.save(commodity);
        return JSONResult.ok();
    }

    @PutMapping
    public JSONResult updateCommodity(){
        return JSONResult.ok();
    }

    @PutMapping("/{id}/{status}")
    public JSONResult updateCommodityStatus(@PathVariable Long id,@PathVariable Integer status){
        System.out.println(id);
        System.out.println(status);
        return JSONResult.ok();
    }


}

