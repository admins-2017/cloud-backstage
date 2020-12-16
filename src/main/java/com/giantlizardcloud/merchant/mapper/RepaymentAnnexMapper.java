package com.giantlizardcloud.merchant.mapper;

import com.giantlizardcloud.merchant.entity.RepaymentAnnex;
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
public interface RepaymentAnnexMapper extends BaseMapper<RepaymentAnnex> {

    /**
     * 根据还款单id查询还款单附件
     * @param rid 还款单id
     * @return 附件集合
     */
    @Select("SELECT annex_id, annex_url FROM merchant_repayment_annex WHERE repayment_id = #{repaymentId}")
    List<RepaymentAnnex> getAllByRepaymentId(@Param("repaymentId") Long rid);
}
