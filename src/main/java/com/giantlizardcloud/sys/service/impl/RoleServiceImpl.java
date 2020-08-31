package com.giantlizardcloud.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.dto.RoleDto;
import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.entity.RoleMenu;
import com.giantlizardcloud.sys.entity.UserRole;
import com.giantlizardcloud.sys.mapper.RoleMapper;
import com.giantlizardcloud.sys.service.IRoleMenuService;
import com.giantlizardcloud.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.sys.service.IUserRoleService;
import com.giantlizardcloud.vo.RoleWithUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public List<RoleWithUserVo> getUserByRoleId(Long roleId) {
        return this.baseMapper.getUserByRoleId(roleId);
    }

    @Override
    @Transactional
    public void insertRoleWithMenu(RoleDto roleDto) {
        Role role = new Role();
        long id = new IdWorker().nextId();
        BeanUtils.copyProperties(roleDto,role);
        role.setRoleId(id);
        this.baseMapper.insert(role);
        log.info(String.valueOf(roleDto.getMenuList().size()));
        //添加权限
        for (int i = 0; i < roleDto.getMenuList().size(); i++) {
            roleMenuService.save(new RoleMenu(id,Long.parseLong(roleDto.getMenuList().get(i))));
        }
    }

    @Override
    public void updateRoleStatus(Long roleId) {
        this.baseMapper.update(null,new UpdateWrapper<Role>().set("del_flag",1).eq("role_id",roleId));
        Long defaultRoleId=this.baseMapper.selectDefaultRole();
        log.info(defaultRoleId.toString());
        //将删除的角色下的所有用户改为默认用户
//        this.userRoleService.update(new UpdateWrapper<UserRole>().set("role_id",defaultRoleId).eq("role_id",roleId));
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        if(!roleDto.getRoleName().equals("")&&!roleDto.getRoleCode().equals("")&&!roleDto.getRoleDescription().equals("")){
            this.baseMapper.update(null,new UpdateWrapper<Role>()
                    .set(roleDto.getRoleName()!=null&&!roleDto.getRoleName().equals(""),"role_name",roleDto.getRoleName())
                    .set(roleDto.getRoleCode()!=null&&!roleDto.getRoleCode().equals(""),"role_code",roleDto.getRoleCode())
                    .set(roleDto.getRoleDescription()!=null&&!roleDto.getRoleDescription().equals(""),"role_description",roleDto.getRoleDescription())
                    .eq("role_id",roleDto.getRoleId()));
        }
        if (roleDto.getMenuList()!=null){
            //这里做更新权限
            System.out.println(roleDto.getMenuList().toString());
        }
    }

    @Override
    public void updateDefaultRole(Long roleId) {
        //将原默认角色设置为普通角色
        this.baseMapper.update(null,new UpdateWrapper<Role>().set("default_role",0).eq("default_role",1));
        //设置默认用户
        this.baseMapper.update(null,new UpdateWrapper<Role>().set("default_role",1).eq("role_id",roleId));
    }
}
