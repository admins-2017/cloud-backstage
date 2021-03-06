package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.merchant.dto.AddSettleDto;
import com.giantlizardcloud.merchant.dto.QuerySettleDto;
import com.giantlizardcloud.merchant.entity.Settle;
import com.giantlizardcloud.merchant.entity.SettleAnnex;
import com.giantlizardcloud.merchant.mapper.SettleMapper;
import com.giantlizardcloud.merchant.service.IClientService;
import com.giantlizardcloud.merchant.service.ISettleAnnexService;
import com.giantlizardcloud.merchant.service.ISettleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.SettleWithAnnexVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SettleServiceImpl extends ServiceImpl<SettleMapper, Settle> implements ISettleService {

    private final ISettleAnnexService annexService;
    private final IClientService clientService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSettle(AddSettleDto dto) {
        Settle settle = new Settle();
        BeanUtils.copyProperties(dto,settle);
        long sid = new IdWorker().nextId();
        settle.setSettleId(sid);
        baseMapper.insert(settle);
        for (String s : dto.getUrls()) {
            annexService.save(new SettleAnnex(s, sid));
        }
        clientService.minusClientArrears(dto.getClientId(),dto.getSettleSum());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invalidSettle(Long sid) {
        Settle settle = this.getOne(new QueryWrapper<Settle>().eq("settle_id", sid));
        clientService.addClientArrears(settle.getClientId(),settle.getSettleSum());
        update(new UpdateWrapper<Settle>().set("settle_status","2").eq("settle_id",sid));
    }

    @Override
    public IPage<SettleWithAnnexVo> getSettleByCondition(QuerySettleDto dto) {
        dto.setPage((dto.getPage()-1)*dto.getSize());
        List<SettleWithAnnexVo> settles = baseMapper.getSettleByCondition(dto);
        IPage<SettleWithAnnexVo> vos = new Page<>();
        vos.setRecords(settles);
        vos.setTotal(count(new QueryWrapper<Settle>()
                        .eq(dto.getSettleNumber()!=null&&!"".equals(dto.getSettleNumber()),"settle_number",dto.getSettleNumber())
                        .eq(dto.getClientId()!=null,"client_id",dto.getClientId())
                        .eq(dto.getSettleStatus()!=null, "settle_status",dto.getSettleStatus())
                        .eq(dto.getSettleMethod()!= null , "settle_method", dto.getSettleMethod())
                        .between(dto.getSettleEndDate()!=null&& dto.getSettleStartDate()!=null
                                        &&!"".equals(dto.getSettleStartDate())&&!"".equals(dto.getSettleEndDate())
                                ,"settle_date",dto.getSettleStartDate(),dto.getSettleEndDate())
                )
        );
        return vos;
    }

    public SettleServiceImpl(ISettleAnnexService annexService, IClientService clientService) {
        this.annexService = annexService;
        this.clientService = clientService;
    }
}
