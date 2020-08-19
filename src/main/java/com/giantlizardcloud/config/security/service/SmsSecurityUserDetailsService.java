package com.giantlizardcloud.config.security.service;

import com.giantlizardcloud.config.security.entity.SecurityUser;
import com.giantlizardcloud.sys.entity.User;
import com.giantlizardcloud.sys.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SmsSecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public SecurityUser loadUserByUsername(String s) throws UsernameNotFoundException {
        // 查询用户信息
        User user =userService.selectUserByPhoneNumber(s);
        if (user!=null){
            // 组装参数
            SecurityUser securityUser = new SecurityUser();
            BeanUtils.copyProperties(user,securityUser);
            return securityUser;
        }
        return null;
    }
}
