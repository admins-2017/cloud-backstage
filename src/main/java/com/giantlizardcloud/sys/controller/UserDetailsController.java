package com.giantlizardcloud.sys.controller;


import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.config.security.entity.SecurityUser;
import com.giantlizardcloud.config.security.service.SecurityUserDetailsService;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import com.giantlizardcloud.dto.UserBasicInformation;
import com.giantlizardcloud.dto.VerifyCodeDto;
import com.giantlizardcloud.sys.entity.LoginRecord;
import com.giantlizardcloud.sys.entity.User;
import com.giantlizardcloud.sys.entity.UserDetails;
import com.giantlizardcloud.sys.service.ILoginRecordService;
import com.giantlizardcloud.sys.service.IUserDetailsService;
import com.giantlizardcloud.sys.service.IUserService;
import com.giantlizardcloud.sys.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@RestController
@RequestMapping("/detail")
@Slf4j
public class UserDetailsController {

    @Autowired
    private IUserDetailsService detailsService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILoginRecordService recordService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 获取用户登录信息
     * @return
     */
    @GetMapping("/{page}/{size}")
    public JSONResult getLoginRecord(@PathVariable Integer page,@PathVariable Integer size){
        Page<LoginRecord> page1 = recordService.page(new Page<>(page,size),new QueryWrapper<LoginRecord>()
                .eq("user_id",SecurityUntil.getUserId()).orderByDesc("record_time"));
        return JSONResult.ok(page1);
    }

    /**
     * 更改用户基础信息
     * @param basicInformation
     * @return
     */
    @PostMapping("/")
    public JSONResult updateUserBasicInformation(UserBasicInformation basicInformation){
        detailsService.update(new UpdateWrapper<UserDetails>()
                .set(basicInformation.getUserDetailsSex()!= null,"user_details_sex",basicInformation.getUserDetailsSex())
                .set(basicInformation.getUserDetailsAddr() != null,"user_details_addr",basicInformation.getUserDetailsAddr())
                .set(basicInformation.getShopId() != null,"shop_id",basicInformation.getShopId())
                .eq("user_id",SecurityUntil.getUserId()));
        return JSONResult.ok("修改成功");
    }


    /**
     * 更换用户密码
     * @param basicInformation
     * @return
     */
    @PutMapping("/pass")
    public JSONResult updateUserPassword(UserBasicInformation basicInformation){

        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", SecurityUntil.getUserId()));

        if (new BCryptPasswordEncoder().matches(basicInformation.getOldPassword(), user.getPassword())) {

            String encode = new BCryptPasswordEncoder().encode(basicInformation.getNewPassword());
            log.info(encode);
            boolean update = userService.update(new UpdateWrapper<User>().set("password", encode).eq("user_id", SecurityUntil.getUserId()));
            return JSONResult.ok(update);
        }else{
            throw new BadCredentialsException("密码不正确");
        }
    }

    @GetMapping("/original/{number}")
    public JSONResult verifyOriginalPhoneNumber(@PathVariable String number) throws ClientException {
        UserDetails details = detailsService.getOne(new QueryWrapper<UserDetails>()
                .eq("user_id", SecurityUntil.getUserId()).eq("user_details_tel", number));
        if (details!=null){
            return JSONResult.sendSmsOk(smsService.sendSms(number),"发送验证码成功");
        }else {
            return JSONResult.errorNofind("手机号错误，请重新输入");
        }
    }

    @GetMapping("/new/{number}")
    public JSONResult verifyNewPhoneNumber(@PathVariable String number) throws ClientException {
        return JSONResult.sendSmsOk(smsService.sendSms(number),"发送验证码成功");
    }


    @GetMapping("/verify/code")
    public JSONResult VerifyCode(String uuid, String code){
        log.info(uuid);
        log.info(code);
        if (!redisOperator.exists(uuid)) {
            return JSONResult.redisExistsError();
        }
        if (!(redisOperator.getString(uuid).equals(code) )){
            return JSONResult.redisResultError();
        }
        return JSONResult.ok("验证短信码成功");
    }

    @PostMapping("/change/phone/{number}")
    public JSONResult changePhoneNumber(@PathVariable String number){
        detailsService.update(new UpdateWrapper<UserDetails>().set("user_details_tel",number).eq("user_id",SecurityUntil.getUserId()));
        return JSONResult.ok("修改手机号成功");
    }

}
