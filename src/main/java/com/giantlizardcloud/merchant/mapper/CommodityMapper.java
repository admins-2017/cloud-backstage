package com.giantlizardcloud.merchant.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.dto.FindCommodityByConditionDto;
import com.giantlizardcloud.merchant.entity.Commodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.vo.CommodityWithClassificationVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-22
 */
public interface CommodityMapper extends BaseMapper<Commodity> {

    @Select("select m.*,mc.classification_code,mc.classification_name,su.username as insert_username,su2.username as update_username from merchant_commodity m\n" +
            "LEFT JOIN merchant_classification mc on m.classification_id = mc.classification_id \n" +
            "LEFT JOIN sys_user su on su.user_id = m.insert_user \n" +
            "LEFT JOIN sys_user su2 on su2.user_id = m.update_user")
    IPage<CommodityWithClassificationVo> getAllCommodityByPage(Page<CommodityWithClassificationVo> voPage);

    Integer getCommodityCountByCondition(FindCommodityByConditionDto dto);

    List<CommodityWithClassificationVo> getCommodityByCondition(FindCommodityByConditionDto dto);

    @Select("select m.*,mc.classification_code,mc.classification_name,su.username as insert_username,su2.username as update_username from merchant_commodity m\n" +
            "LEFT JOIN merchant_classification mc on m.classification_id = mc.classification_id \n" +
            "LEFT JOIN sys_user su on su.user_id = m.insert_user \n" +
            "LEFT JOIN sys_user su2 on su2.user_id = m.update_user WHERE mc.classification_id = #{id}")
    List<CommodityWithClassificationVo> getAllCommodityByClassificationId(@Param("id") Long id);

    List<CommodityWithClassificationVo> getAllCommodityInClassificationIds(@Param("ids") List<Object> classifications);
}
