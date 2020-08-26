package com.giantlizardcloud.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.vo.MenuTreeVo;
import com.giantlizardcloud.vo.UserDetailsWithRoleAndShopVo;
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

    @Select("select su.user_id, su.username,su.status,sr.role_id,sr.role_name ,\n" +
            "\tsud.user_details_sex,sud.user_details_addr,sud.user_details_mail,sud.user_details_tel \n" +
            "\t,sud.shop_id,ms.shop_name from sys_user su\n" +
            " left join sys_user_role sur\n" +
            "\ton su.user_id = sur.user_id\n" +
            " join sys_role sr\n" +
            "\ton sr.role_id = sur.role_id\n" +
            " left join sys_user_details sud\n" +
            " on  sud.user_id = su.user_id\n" +
            " left join merchant_shop ms on sud.shop_id = ms.shop_id where su.status != 'DEL'")
    IPage<UserDetailsWithRoleAndShopVo> getAll(Page<UserDetailsWithRoleAndShopVo> voPage);

    @Select("select su.user_id, su.username,su.status,sr.role_id,sr.role_name ,\n" +
            "\tsud.user_details_sex,sud.user_details_addr,sud.user_details_mail,sud.user_details_tel \n" +
            "\t,sud.shop_id,ms.shop_name from sys_user su\n" +
            " left join sys_user_role sur\n" +
            "\ton su.user_id = sur.user_id\n" +
            " join sys_role sr\n" +
            "\ton sr.role_id = sur.role_id\n" +
            " left join sys_user_details sud\n" +
            " on  sud.user_id = su.user_id\n" +
            " left join merchant_shop ms on sud.shop_id = ms.shop_id where su.username like #{name} ")
    IPage<UserDetailsWithRoleAndShopVo> getUserByName(@Param("name") String name, Page<UserDetailsWithRoleAndShopVo> voPage);
}
