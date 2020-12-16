package com.giantlizardcloud.merchant.service.impl;

import com.giantlizardcloud.merchant.dto.AddRepaymentDto;
import com.giantlizardcloud.merchant.dto.QueryRepaymentDto;
import com.giantlizardcloud.merchant.entity.Repayment;
import com.giantlizardcloud.merchant.mapper.RepaymentMapper;
import com.giantlizardcloud.merchant.service.IRepaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.RepaymentWithAnnexVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-12-16
 */
@Service
public class RepaymentServiceImpl extends ServiceImpl<RepaymentMapper, Repayment> implements IRepaymentService {

    @Override
    public List<RepaymentWithAnnexVo> getRepayment(QueryRepaymentDto dto) {
        return null;
    }

    @Override
    public void invalidRepayment(Long rid) {

    }

    @Override
    public void addRepayment(AddRepaymentDto dto) {

    }
}
