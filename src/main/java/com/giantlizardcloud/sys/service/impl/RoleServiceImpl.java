package com.giantlizardcloud.sys.service.impl;

import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.mapper.RoleMapper;
import com.giantlizardcloud.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
