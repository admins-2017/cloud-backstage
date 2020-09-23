package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import com.giantlizardcloud.merchant.dto.AddCommodityDto;
import com.giantlizardcloud.merchant.dto.FindCommodityByConditionDto;
import com.giantlizardcloud.merchant.dto.UpdateCommodityDto;
import com.giantlizardcloud.merchant.entity.Commodity;
import com.giantlizardcloud.merchant.service.ICommodityService;
import com.giantlizardcloud.merchant.vo.CommodityWithClassificationVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/commodity")
@Api(value = "商品管理", tags = "商品明细对应操作")
@Slf4j
public class CommodityController {

    private final ICommodityService commodityService;

    public CommodityController(ICommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @GetMapping("/{page}/{size}")
    public JSONResult getAllCommodity(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<CommodityWithClassificationVo> commodityList = commodityService.getAllCommodityByPage(page, size);
        return JSONResult.ok(commodityList);
    }

    @GetMapping
    public JSONResult getCommodityByCondition(FindCommodityByConditionDto dto) {
        log.info(dto.toString());
        IPage<CommodityWithClassificationVo> list = commodityService.getCommodityByCondition(dto);
        return JSONResult.ok(list);
    }

    @PostMapping
    public JSONResult addCommodity(AddCommodityDto dto) {
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(dto, commodity);
        commodityService.save(commodity);
        return JSONResult.ok("添加商品成功");
    }

    @PutMapping
    public JSONResult updateCommodityDetail(UpdateCommodityDto dto) {
        commodityService.update(new UpdateWrapper<Commodity>()
                .set(dto.getCommodityName() != null && !dto.getCommodityName().equals(""), "commodity_name", dto.getCommodityName())
                .set(dto.getCommodityNumber() != null && !dto.getCommodityNumber().equals(""), "commodity_number", dto.getCommodityNumber())
                .set(dto.getCommodityPicture() != null && !dto.getCommodityPicture().equals(""), "commodity_picture", dto.getCommodityPicture())
                .set(dto.getCommoditySellingPrice() != null && !dto.getCommoditySellingPrice().equals(""), "commodity_selling_price", dto.getCommoditySellingPrice())
                .set(dto.getCommodityUnit() != null && !dto.getCommodityUnit().equals(""), "commodity_unit", dto.getCommodityUnit())
                .set(dto.getCommodityDescription() != null && !dto.getCommodityDescription().equals(""), "commodity_description", dto.getCommodityDescription())
                .set(dto.getClassificationId() != null && !dto.getClassificationId().equals(""), "classification_id", dto.getClassificationId())
                .set("update_time", LocalDateTime.now())
                .set("update_user", SecurityUntil.getUserId())
                .eq("commodity_id", dto.getCommodityId()));
        return JSONResult.ok("修改商铺成功");
    }

    @PutMapping("/{id}/{status}")
    public JSONResult updateCommodityStatus(@PathVariable Long id, @PathVariable Integer status) {
        /**
         * status 1 下架 2 停用 3 上架 4 恢复商品
         */
        String result = "";
        switch (status) {
            case 1:
                commodityService.update(new UpdateWrapper<Commodity>().set("commodity_status", 1).eq("commodity_id", id));
                result = "下架商品完成";
                break;
            case 2:
                commodityService.update(new UpdateWrapper<Commodity>().set("commodity_status", 2).eq("commodity_id", id));
                result = "停用商品完成";
                break;
            case 3:
            case 4:
                result = "恢复商品完成";
                commodityService.update(new UpdateWrapper<Commodity>().set("commodity_status", 0).eq("commodity_id", id));
                break;
        }
        return JSONResult.ok(result);
    }


}

