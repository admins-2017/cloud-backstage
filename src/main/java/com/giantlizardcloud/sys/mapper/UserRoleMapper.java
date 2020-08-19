package com.giantlizardcloud.sys.mapper;

import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户与角色关系表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Role> getRoles(Long userId);
}
