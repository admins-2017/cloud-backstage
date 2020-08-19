package com.giantlizardcloud.sys.service.impl;

import com.giantlizardcloud.config.security.vo.LoginSuccessVo;
import com.giantlizardcloud.sys.entity.UserDetails;
import com.giantlizardcloud.sys.mapper.UserDetailsMapper;
import com.giantlizardcloud.sys.service.IUserDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@Service
public class UserDetailsServiceImpl extends ServiceImpl<UserDetailsMapper, UserDetails> implements IUserDetailsService {

    @Override
    public LoginSuccessVo getUserDetailsById(Long userId) {
        return this.baseMapper.getUserDetailsById(userId);
    }

    @Override
    public void updateUserPassword(String phoneNumber, String encode) {
        this.baseMapper.updateUserPassword(phoneNumber,encode);
    }
}
