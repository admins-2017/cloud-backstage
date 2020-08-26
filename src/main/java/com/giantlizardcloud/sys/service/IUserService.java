package com.giantlizardcloud.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.dto.InsertUserDto;
import com.giantlizardcloud.dto.UpdateUserDto;
import com.giantlizardcloud.sys.entity.Menu;
import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.vo.MenuTreeVo;
import com.giantlizardcloud.vo.UserDetailsWithRoleAndShopVo;

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

    List<MenuTreeVo> selectBasisMenuTreeByUserId(Long userId);

    IPage<UserDetailsWithRoleAndShopVo> getAllUser(Page<UserDetailsWithRoleAndShopVo> voPage);

    IPage<UserDetailsWithRoleAndShopVo> getUserByName(String name,Page<UserDetailsWithRoleAndShopVo> voPage);

    IPage<UserDetailsWithRoleAndShopVo> getUserByShop(Long shopId,Page<UserDetailsWithRoleAndShopVo> voPage);

    void insertUser(InsertUserDto dto);

    void updateUser(UpdateUserDto dto);
}
