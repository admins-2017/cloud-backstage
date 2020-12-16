package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.dto.QueryRepaymentDto;
import com.giantlizardcloud.merchant.entity.Repayment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.RepaymentWithAnnexVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-12-16
 */
public interface RepaymentMapper extends BaseMapper<Repayment> {

    /**
     * 根据查询条件分页查询还款单
     * @param dto 查询条件
     * @return 还款单集合
     */
    List<RepaymentWithAnnexVo> getRepayment(QueryRepaymentDto dto);
}
