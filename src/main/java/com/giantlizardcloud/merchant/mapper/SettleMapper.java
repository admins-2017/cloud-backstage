package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.dto.QuerySettleDto;
import com.giantlizardcloud.merchant.entity.Settle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.SettleWithAnnexVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-12-16
 */
public interface SettleMapper extends BaseMapper<Settle> {

    List<SettleWithAnnexVo> getSettleByCondition(QuerySettleDto dto);
}
