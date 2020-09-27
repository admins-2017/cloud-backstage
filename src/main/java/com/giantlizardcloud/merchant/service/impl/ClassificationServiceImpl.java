package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.utils.ParseClassificationTreeUtil;
import com.giantlizardcloud.merchant.entity.Classification;
import com.giantlizardcloud.merchant.mapper.ClassificationMapper;
import com.giantlizardcloud.merchant.service.IClassificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.ClassificationVo;
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
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper, Classification> implements IClassificationService {

    @Override
    public Page<ClassificationVo> getClassificationByPage(Integer page, Integer size) {
        Page<Classification> parentPage = this.page(new Page<>(page, size), new QueryWrapper<Classification>().eq("parent_id", 0));
        List<Classification> list = this.list(new QueryWrapper<Classification>().ne("parent_id", 0));
        Page<ClassificationVo> voPage = ParseClassificationTreeUtil.parseMenuTree(parentPage, list);
        return voPage;
    }

    @Override
    public List<ClassificationVo> getAllClassification() {
        List<Classification> parentList = this.list(new QueryWrapper<Classification>().eq("parent_id", 0));
        List<Classification> list = this.list(new QueryWrapper<Classification>().ne("parent_id", 0));
        List<ClassificationVo> voList = ParseClassificationTreeUtil.parseMenuTree(parentList, list);
        return voList;
    }
}
