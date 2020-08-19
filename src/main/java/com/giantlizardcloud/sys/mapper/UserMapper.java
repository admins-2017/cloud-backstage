package com.giantlizardcloud.sys.mapper;

import com.giantlizardcloud.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.vo.MenuTreeVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select su.* from sys_user su\n" +
            "\tleft join sys_user_details sud \n" +
            "\ton su.user_id = sud.user_id\n" +
            "\twhere sud.user_details_tel = #{number}")
    User selectUserByPhoneNumber(@Param("number") String s);

    List<MenuTreeVo> selectMenuTreeByUserId(Long userId);

    List<MenuTreeVo> selectBasisMenuTreeByUserId(Long userId);

}
