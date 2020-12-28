package com.giantlizardcloud.merchant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.merchant.dto.AddRepaymentDto;
import com.giantlizardcloud.merchant.dto.QueryRepaymentDto;
import com.giantlizardcloud.merchant.entity.Repayment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.merchant.vo.RepaymentWithAnnexVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-12-16
 */
public interface IRepaymentService extends IService<Repayment> {

    /**
     * 获取还款单分页信息
     * @param dto 查询条件
     * @return 结果集
     */
    IPage<RepaymentWithAnnexVo> getRepayment(QueryRepaymentDto dto);

    /**
     * 作废还款单
     * @param rid 单号id
     */
    void invalidRepayment(Long rid);

    /**
     * 新增还款单
     * @param dto 还款单
     */
    void addRepayment(AddRepaymentDto dto);
}
