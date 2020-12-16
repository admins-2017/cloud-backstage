package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.giantlizardcloud.merchant.dto.AddRepaymentDto;
import com.giantlizardcloud.merchant.dto.QueryRepaymentDto;
import com.giantlizardcloud.merchant.entity.Repayment;
import com.giantlizardcloud.merchant.mapper.RepaymentMapper;
import com.giantlizardcloud.merchant.service.IRepaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.RepaymentWithAnnexVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return baseMapper.getRepayment(dto);
    }

    @Override
    public void invalidRepayment(Long rid) {
        baseMapper.update(null,new UpdateWrapper<Repayment>()
                .set("repayment_status",2).eq("repayment_id",rid));
    }

    @Override
    public void addRepayment(AddRepaymentDto dto) {
        Repayment repayment = new Repayment();
        BeanUtils.copyProperties(dto,repayment);
        repayment.setInsertUser(5080493493634768L);
        repayment.setInsertTime(LocalDateTime.now());
        baseMapper.insert(repayment);
    }
}
