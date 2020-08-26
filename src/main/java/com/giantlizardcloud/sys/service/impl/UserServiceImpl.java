package com.giantlizardcloud.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.dto.InsertUserDto;
import com.giantlizardcloud.dto.UpdateUserDto;
import com.giantlizardcloud.sys.entity.*;
import com.giantlizardcloud.sys.mapper.RoleMenuMapper;
import com.giantlizardcloud.sys.mapper.UserDetailsMapper;
import com.giantlizardcloud.sys.mapper.UserMapper;
import com.giantlizardcloud.sys.mapper.UserRoleMapper;
import com.giantlizardcloud.sys.service.IUserDetailsService;
import com.giantlizardcloud.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.vo.MenuTreeVo;
import com.giantlizardcloud.vo.UserDetailsWithRoleAndShopVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserDetailsMapper detailsMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private IUserDetailsService detailsService;

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

    @Override
    public List<MenuTreeVo> selectBasisMenuTreeByUserId(Long userId) {
        return this.baseMapper.selectBasisMenuTreeByUserId(userId);

    }

    @Override
    public IPage<UserDetailsWithRoleAndShopVo> getAllUser(Page<UserDetailsWithRoleAndShopVo> voPage) {
        return this.baseMapper.getAll(voPage);
    }

    @Override
    public IPage<UserDetailsWithRoleAndShopVo> getUserByName(String name,Page<UserDetailsWithRoleAndShopVo> voPage) {
        name= name+"%";
        return this.baseMapper.getUserByName(name,voPage);
    }

    @Override
    public IPage<UserDetailsWithRoleAndShopVo> getUserByShop(Long shopId, Page<UserDetailsWithRoleAndShopVo> voPage) {
        return this.baseMapper.getUserByShop(shopId,voPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(InsertUserDto dto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        log.info(encode);
        User user =  new User();
        Long userId = new IdWorker().nextId();
        user.setUserId(userId);
        user.setStatus("NORMAL");
        user.setPassword(encode);
        user.setUsername(dto.getUsername());
        this.baseMapper.insert(user);
        log.info(user.toString());
        UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(dto,userDetails);
        userDetails.setUserId(userId);
        userDetails.setUserDetailsUrl("http://qf30yvzam.hn-bkt.clouddn.com/Fhd44skS5yUk5eXxCcBbJJ8c7Rbj");
        log.info(userDetails.toString());
        detailsMapper.insert(userDetails);
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(dto.getRoleId());
        userRoleMapper.insert(userRole);
        log.info(userRole.toString());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UpdateUserDto dto) {
        detailsService.update(new UpdateWrapper<UserDetails>()
                .set(dto.getUserDetailsAddr()!="","user_details_addr",dto.getUserDetailsAddr())
                .set(dto.getUserDetailsMail()!="","user_details_mail",dto.getUserDetailsMail())
                .set(dto.getUserDetailsSex()!=null,"user_details_sex",dto.getUserDetailsSex())
                .set(dto.getShopId()!=null,"shop_id",dto.getShopId())
                .eq("user_id",dto.getUserId()));
        if (dto.getRoleId()!=null){
            UserRole userRole = new UserRole();
            userRole.setUserId(dto.getUserId());
            userRole.setRoleId(dto.getRoleId());
            userRoleMapper.update(userRole,new UpdateWrapper<>());
        }
    }

}
