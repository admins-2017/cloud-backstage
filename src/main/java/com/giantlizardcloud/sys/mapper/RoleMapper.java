package com.giantlizardcloud.sys.mapper;

import com.giantlizardcloud.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.vo.RoleWithUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select sur.role_id,sr.role_name ,su.username,su.status,sud.user_details_addr,\n" +
            "sud.user_details_tel,ms.shop_name from sys_user_role sur\n" +
            "left join sys_user su \n" +
            "on su.user_id = sur.user_id\n" +
            "left join sys_role sr\n" +
            "on sur.role_id = sr.role_id\n" +
            "left join sys_user_details sud\n" +
            "on su.user_id = sud.user_id\n" +
            "left join merchant_shop ms\n" +
            "on ms.shop_id = sud.shop_id\n" +
            "where sur.role_id = #{roleId}")
    List<RoleWithUserVo> getUserByRoleId(@Param("roleId") Long roleId);

    @Select("select role_id from sys_role where default_role = 1")
    Long selectDefaultRole();
}
