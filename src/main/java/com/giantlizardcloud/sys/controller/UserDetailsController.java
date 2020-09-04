package com.giantlizardcloud.sys.controller;


import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import com.giantlizardcloud.dto.UserBasicInformation;
import com.giantlizardcloud.sys.entity.LoginRecord;
import com.giantlizardcloud.sys.entity.User;
import com.giantlizardcloud.sys.entity.UserDetails;
import com.giantlizardcloud.sys.service.ILoginRecordService;
import com.giantlizardcloud.sys.service.IUserDetailsService;
import com.giantlizardcloud.sys.service.IUserService;
import com.giantlizardcloud.sys.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


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
@Api(value = "用户详情管理",tags = "用户详情对应操作")
@Slf4j
public class UserDetailsController {

    private final IUserDetailsService detailsService;

    private final IUserService userService;

    private final ILoginRecordService recordService;

    private final SmsService smsService;

    private final RedisOperator redisOperator;

    public UserDetailsController(IUserDetailsService detailsService, IUserService userService, ILoginRecordService recordService, SmsService smsService, RedisOperator redisOperator) {
        this.detailsService = detailsService;
        this.userService = userService;
        this.recordService = recordService;
        this.smsService = smsService;
        this.redisOperator = redisOperator;
    }

    /**
     * 获取用户登录信息
     */
    @GetMapping("/{page}/{size}")
    @ApiOperation(value="获取登录记录",notes = "页码，条数")
    public JSONResult getLoginRecord(@PathVariable Integer page,@PathVariable Integer size){
        Page<LoginRecord> page1 = recordService.page(new Page<>(page,size),new QueryWrapper<LoginRecord>()
                .eq("user_id",SecurityUntil.getUserId()).orderByDesc("record_time"));
        return JSONResult.ok(page1);
    }

    /**
     * 更改用户基础信息
     */
    @PostMapping("/")
    @ApiOperation(value="修改用户详情",notes = "用户详情dto")
    public JSONResult updateUserBasicInformation(UserBasicInformation basicInformation){
        detailsService.update(new UpdateWrapper<UserDetails>()
                .set(basicInformation.getUserDetailsSex()!= null,"user_details_sex",basicInformation.getUserDetailsSex())
                .set(basicInformation.getUserDetailsAddr() != null,"user_details_addr",basicInformation.getUserDetailsAddr())
                .set(basicInformation.getShopId() != null,"shop_id",basicInformation.getShopId())
                .eq("user_id",SecurityUntil.getUserId()));
        return JSONResult.ok("修改成功");
    }


    @PutMapping("/pass")
    @ApiOperation(value="修改用户密码",notes = "用户详情dto")
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
    @ApiOperation(value="验证手机号",notes = "手机号")
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
    @ApiOperation(value="验证新手机号",notes = "手机号")
    public JSONResult verifyNewPhoneNumber(@PathVariable String number) throws ClientException {
        return JSONResult.sendSmsOk(smsService.sendSms(number),"发送验证码成功");
    }


    @GetMapping("/verify/code")
    @ApiOperation(value="验证手机验证码",notes = "验证码id，验证码结果")
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
    @ApiOperation(value="修改手机号",notes = "手机号")
    public JSONResult changePhoneNumber(@PathVariable String number){
        detailsService.update(new UpdateWrapper<UserDetails>().set("user_details_tel",number).eq("user_id",SecurityUntil.getUserId()));
        return JSONResult.ok("修改手机号成功");
    }

}
