package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.giantlizardcloud.merchant.service.ISupplierService;
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
    private final ISupplierService supplierService;

    @Override
    public IPage<RepaymentWithAnnexVo> getRepayment(QueryRepaymentDto dto) {
        dto.setPage((dto.getPage()-1)*dto.getSize());
        List<RepaymentWithAnnexVo> repayment = baseMapper.getRepayment(dto);
        IPage<RepaymentWithAnnexVo> repayments = new Page<>();
        repayments.setRecords(repayment);
        repayments.setTotal(count(new QueryWrapper<Repayment>()
                .eq(dto.getRepaymentNumber()!=null&&!"".equals(dto.getRepaymentNumber()),"repayment_number",dto.getRepaymentNumber())
                .eq(dto.getSupplierId()!=null,"supplier_id",dto.getSupplierId())
                .eq(dto.getRepaymentStatus()!=null, "repayment_status",dto.getRepaymentStatus())
                .eq(dto.getRepaymentMethod()!= null , "repayment_method", dto.getRepaymentMethod())
                .between(dto.getRepaymentStartDate()!=null&&dto.getRepaymentEndDate()!=null&&
                        !"".equals(dto.getRepaymentEndDate())&&!"".equals(dto.getRepaymentStartDate())
                ,"repayment_date",dto.getRepaymentStartDate(),dto.getRepaymentEndDate()))
        );
        return repayments;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invalidRepayment(Long rid) {
        baseMapper.update(null,new UpdateWrapper<Repayment>()
                .set("repayment_status",2).set("repayment_invalid_user", SecurityUntil.getUserId())
                .set("repayment_invalid_time",LocalDateTime.now()).eq("repayment_id",rid));
        Repayment repayment = baseMapper.selectOne(new QueryWrapper<Repayment>()
                .select("supplier_id", "repayment_sum").eq("repayment_id", rid));
        supplierService.addSupplierArrears(repayment.getSupplierId(),repayment.getRepaymentSum());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRepayment(AddRepaymentDto dto) {
        Repayment repayment = new Repayment();
        BeanUtils.copyProperties(dto,repayment);
        long id = new IdWorker().nextId();
        repayment.setRepaymentId(id);
        baseMapper.insert(repayment);
        if (dto.getChildren().size()> 0){
            dto.getChildren().forEach(s -> annexService.save(new RepaymentAnnex(s,id)));
        }
        supplierService.minusSupplierArrears(dto.getSupplierId(),dto.getRepaymentSum());
    }

    public RepaymentServiceImpl(IRepaymentAnnexService annexService,ISupplierService supplierService) {
        this.annexService = annexService;
        this.supplierService = supplierService;
    }
}
