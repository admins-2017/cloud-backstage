package com.giantlizardcloud.config.security.handler;

import com.giantlizardcloud.config.aspect.SysLoginLog;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.security.entity.SecurityUser;
import com.giantlizardcloud.config.security.jwt.JWTConfig;
import com.giantlizardcloud.config.security.jwt.JWTTokenUtil;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import com.giantlizardcloud.config.security.vo.LoginSuccessVo;
import com.giantlizardcloud.sys.service.IUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录成功处理类
 * @Author Sans
 * @CreateTime 2019/10/3 9:13
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private IUserDetailsService detailsService;
//    @Autowired
//    private RedisOperator redisOperator;

    /**
     * 登录成功返回结果
     * @Author kang
     * @CreateTime 2019/10/3 9:27
     */
    @Override
    @SysLoginLog
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        // 组装JWT
        SecurityUser userEntity =  (SecurityUser) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(userEntity);
        token = JWTConfig.tokenPrefix + token;
        //为前端提供返回vo
        LoginSuccessVo successVo = new LoginSuccessVo();
        successVo=detailsService.getUserDetailsById(userEntity.getUserId());
        BeanUtils.copyProperties(userEntity,successVo);
        successVo.setToken(token);

//        if (redisOperator.exists(RedisIndexEnum.indexDetailsRedisKey.getCode())) {
//            redisOperator.hashIncrBy(RedisIndexEnum.indexDetailsRedisKey.getCode(), "day", 1);
//            redisOperator.hashIncrBy(RedisIndexEnum.indexDetailsRedisKey.getCode(), "month", 1);
//        }
        //组装参数
//        SecurityUntil securityUntil = new SecurityUntil();


        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>(3);
        resultData.put("meta", JSONResult.login());
        resultData.put("data",successVo);

        JSONResult.responseJson(response,resultData);
    }
}