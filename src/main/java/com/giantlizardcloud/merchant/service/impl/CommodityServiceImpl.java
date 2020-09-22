package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.entity.Commodity;
import com.giantlizardcloud.merchant.mapper.CommodityMapper;
import com.giantlizardcloud.merchant.service.ICommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.CommodityWithClassificationVo;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage<CommodityWithClassificationVo> getAllCommodityByPage(Integer page, Integer size) {
        Page<CommodityWithClassificationVo> voPage = new Page<>(page, size);
        return this.baseMapper.getAllCommodityByPage(voPage);
    }
}
