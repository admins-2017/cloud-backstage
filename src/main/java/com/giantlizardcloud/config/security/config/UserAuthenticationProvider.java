package com.giantlizardcloud.config.security.config;

import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.config.security.entity.SecurityUser;
import com.giantlizardcloud.config.security.handler.LoginCodeException;
import com.giantlizardcloud.config.security.handler.LoginResultException;
import com.giantlizardcloud.config.security.service.SecurityUserDetailsService;
import com.giantlizardcloud.config.security.service.SmsSecurityUserDetailsService;
import com.giantlizardcloud.config.security.until.LoginUntil;
import com.giantlizardcloud.sys.entity.Role;
import com.giantlizardcloud.sys.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义登录验证
 * @Author Sans
 * @CreateTime 2019/10/1 19:11
 */
@Component
@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SecurityUserDetailsService userDetailsService;
    @Autowired
    private SmsSecurityUserDetailsService smsSecurityUserDetailsService;
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisOperator redisOperator;
    @Autowired
    private LoginUntil loginUntil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes())
                .getRequest();
        String type = request.getParameter("type");
        if(type.equals("1")) {
            return loginUntil.loginByUserName(request,authentication);
        }else {
            return loginUntil.loginBySms(request);
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
