package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.entity.SettleAnnex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-12-16
 */
public interface SettleAnnexMapper extends BaseMapper<SettleAnnex> {

    /**
     * 根据接清单id获取附件列表
     * @param settleId
     * @return
     */
    @Select("select annex_url from merchant_settle_annex where settle_id = #{settleId}")
    List<String> getAllAnnexBySettleId(@Param("settleId") Long settleId);
}
