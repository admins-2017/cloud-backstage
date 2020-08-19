package com.giantlizardcloud.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.sys.entity.Menu;
import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.entity.User;
import com.giantlizardcloud.sys.mapper.RoleMenuMapper;
import com.giantlizardcloud.sys.mapper.UserMapper;
import com.giantlizardcloud.sys.mapper.UserRoleMapper;
import com.giantlizardcloud.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.vo.MenuTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public User selectUserByName(String username) {
        return  this.baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public List<Role> selectRoleByUserId(Long userId) {
        return userRoleMapper.getRoles(userId);
    }

    @Override
    public List<Menu> selectMenuByUserId(Long userId) {
        return roleMenuMapper.getMenus(userId);
    }

    @Override
    public User selectUserByPhoneNumber(String s) {
        return this.baseMapper.selectUserByPhoneNumber(s);
    }

    @Override
    public List<MenuTreeVo> selectMenuTreeByUserId(Long userId) {
        return this.baseMapper.selectMenuTreeByUserId(userId);
    }
}
