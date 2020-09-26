package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.dto.FindCommodityByConditionDto;
import com.giantlizardcloud.merchant.entity.Classification;
import com.giantlizardcloud.merchant.entity.Commodity;
import com.giantlizardcloud.merchant.mapper.CommodityMapper;
import com.giantlizardcloud.merchant.service.IClassificationService;
import com.giantlizardcloud.merchant.service.ICommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.CommodityWithClassificationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-22
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements ICommodityService {

    private final IClassificationService classificationService;

    public CommodityServiceImpl(IClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @Override
    public IPage<CommodityWithClassificationVo> getAllCommodityByPage(Integer page, Integer size) {
        Page<CommodityWithClassificationVo> voPage = new Page<>(page, size);
        return this.baseMapper.getAllCommodityByPage(voPage);
    }

    @Override
    public IPage<CommodityWithClassificationVo> getCommodityByCondition(FindCommodityByConditionDto dto) {
        dto.setPage((dto.getPage()-1)*dto.getSize());
        List<CommodityWithClassificationVo> list = this.baseMapper.getCommodityByCondition(dto);
        Integer count = this.baseMapper.getCommodityCountByCondition(dto);
        IPage<CommodityWithClassificationVo> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<CommodityWithClassificationVo> getCommodityByClassification(Long id) {
        List<Object> classifications = classificationService.listObjs(new QueryWrapper<Classification>().select("classification_id").eq("parent_id", id));
        List<CommodityWithClassificationVo> list = null;
        if (classifications.size()==0){
            list = this.baseMapper.getAllCommodityByClassificationId(id);
        }else{
            list = this.baseMapper.getAllCommodityInClassificationIds(classifications);
        }
        return list;
    }
}
