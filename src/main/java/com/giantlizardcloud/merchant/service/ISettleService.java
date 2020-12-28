package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.merchant.dto.AddSettleDto;
import com.giantlizardcloud.merchant.dto.QuerySettleDto;
import com.giantlizardcloud.merchant.entity.Settle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.SettleWithAnnexVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-12-16
 */
public interface ISettleService extends IService<Settle> {

    /**
     * 添加结清单
     * @param dto 结清单详情
     */
    void addSettle(AddSettleDto dto);

    /**
     * 作废结清单
     * @param sid 结清单id
     */
    void invalidSettle(Long sid);

    /**
     * 获取结清单详情
     * @param dto 查询条件
     * @return 结清单列表
     */
    IPage<SettleWithAnnexVo> getSettleByCondition(QuerySettleDto dto);
}
