package com.giantlizardcloud.sys.service.impl;

import com.giantlizardcloud.sys.entity.UserRole;
import com.giantlizardcloud.sys.mapper.UserRoleMapper;
import com.giantlizardcloud.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色关系表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
