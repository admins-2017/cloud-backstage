package com.giantlizardcloud.sys.service;

import com.giantlizardcloud.dto.RoleDto;
import com.giantlizardcloud.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.vo.RoleWithUserVo;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface IRoleService extends IService<Role> {

    List<RoleWithUserVo> getUserByRoleId(Long roleId);

    void insertRoleWithMenu(RoleDto roleDto);

    void updateRoleStatus(Long roleId);

    void updateRole(RoleDto roleDto);

    void updateDefaultRole(Long roleId);
}
