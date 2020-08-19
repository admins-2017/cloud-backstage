package com.giantlizardcloud.sys.service;

import com.giantlizardcloud.sys.entity.Menu;
import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.vo.MenuTreeVo;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface IUserService extends IService<User> {

    User selectUserByName(String username);

    List<Role> selectRoleByUserId(Long userId);

    List<Menu> selectMenuByUserId(Long userId);

    User selectUserByPhoneNumber(String s);

    List<MenuTreeVo> selectMenuTreeByUserId(Long userId);
}
