package com.giantlizardcloud.merchant.service.impl;

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

        dto.getUrls().forEach(s -> {
            annexService.save(new SettleAnnex(s,sid));
        });

        clientService.minusClientArrears(dto.getClientId(),dto.getSettleSum());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invalidSettle(Integer sid) {
        clientService.addClientArrears(1L,100.0);
    }

    @Override
    public List<SettleWithAnnexVo> getSettleByCondition(QuerySettleDto dto) {
        return null;
    }

    public SettleServiceImpl(ISettleAnnexService annexService, IClientService clientService) {
        this.annexService = annexService;
        this.clientService = clientService;
    }
}
