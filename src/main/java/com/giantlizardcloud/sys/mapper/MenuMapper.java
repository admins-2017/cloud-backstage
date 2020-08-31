package com.giantlizardcloud.sys.mapper;

import com.giantlizardcloud.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.vo.MenuTreeVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT DISTINCT sm.menu_id,sm.name,sm.permission,sm.path,sm.parent_id,i_con,sm.type FROM sys_role_menu srm\n" +
            "\t\t\tLEFT JOIN sys_menu sm ON srm.menu_id = sm.menu_id\n" +
            "\t\t\tLEFT JOIN sys_role sr on srm.role_id = sr.role_id \n" +
            "\t\tWHERE sr.role_id = 2")
    List<MenuTreeVo> getMenuByRoleId(Long id);
}
