package com.giantlizardcloud.sys.mapper;

import com.giantlizardcloud.sys.entity.Menu;
import com.giantlizardcloud.sys.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色与权限关系表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Menu> getMenus(Long userId);

    @Select("SELECT menu_id FROM sys_role_menu WHERE role_id = #{id} ")
    List<Long> getRoleMenuList(@Param("id") Long id);
}
