package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.entity.Classification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.ClassificationVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-22
 */
public interface IClassificationService extends IService<Classification> {

    Page<ClassificationVo> getClassificationByPage(Integer page, Integer size);
}
