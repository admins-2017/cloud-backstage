package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.merchant.dto.AddRepaymentDto;
import com.giantlizardcloud.merchant.dto.QueryRepaymentDto;
import com.giantlizardcloud.merchant.entity.Repayment;
import com.giantlizardcloud.merchant.entity.RepaymentAnnex;
import com.giantlizardcloud.merchant.mapper.RepaymentMapper;
import com.giantlizardcloud.merchant.service.IRepaymentAnnexService;
import com.giantlizardcloud.merchant.service.IRepaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.RepaymentWithAnnexVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final IRepaymentAnnexService annexService;

    @Override
    public List<RepaymentWithAnnexVo> getRepayment(QueryRepaymentDto dto) {
        return baseMapper.getRepayment(dto);
    }

    @Override
    public void invalidRepayment(Long rid) {
        baseMapper.update(null,new UpdateWrapper<Repayment>()
                .set("repayment_status",2).set("repayment_invalid_user", SecurityUntil.getUserId())
                .set("repayment_invalid_time",LocalDateTime.now()).eq("repayment_id",rid));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRepayment(AddRepaymentDto dto) {
        Repayment repayment = new Repayment();
        BeanUtils.copyProperties(dto,repayment);
        long id = new IdWorker().nextId();
        repayment.setRepaymentId(id);
        baseMapper.insert(repayment);
        System.out.println(dto.getChildren().size()> 0);
        if (dto.getChildren().size()> 0){
            dto.getChildren().forEach(s -> annexService.save(new RepaymentAnnex(s,id)));
        }

    }

    public RepaymentServiceImpl(IRepaymentAnnexService annexService) {
        this.annexService = annexService;
    }
}
