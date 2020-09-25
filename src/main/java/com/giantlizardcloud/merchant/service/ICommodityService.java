package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.merchant.dto.FindCommodityByConditionDto;
import com.giantlizardcloud.merchant.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.CommodityWithClassificationVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-22
 */
public interface ICommodityService extends IService<Commodity> {

    IPage<CommodityWithClassificationVo> getAllCommodityByPage(Integer page, Integer size);

    IPage<CommodityWithClassificationVo> getCommodityByCondition(FindCommodityByConditionDto dto);

    List<Commodity> getCommodityByClassification(Long id);
}
