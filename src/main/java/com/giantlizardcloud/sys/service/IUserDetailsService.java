package com.giantlizardcloud.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.security.vo.LoginSuccessVo;
import com.giantlizardcloud.sys.entity.UserDetails;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface IUserDetailsService extends IService<UserDetails> {

    LoginSuccessVo getUserDetailsById(Long userId);

    void updateUserPassword(String phoneNumber, String encode);
}
