package com.giantlizardcloud.sys.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.aspect.IpUtil;
import com.giantlizardcloud.config.aspect.SysLoginLog;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.config.restTemplate.RestTemplateConfig;
import com.giantlizardcloud.dto.ChangePasswordDto;
import com.giantlizardcloud.dto.InsertUserDto;
import com.giantlizardcloud.dto.UpdateUserDto;
import com.giantlizardcloud.dto.VerifyCodeDto;
import com.giantlizardcloud.sys.entity.User;
import com.giantlizardcloud.sys.entity.UserDetails;
import com.giantlizardcloud.sys.service.IUserDetailsService;
import com.giantlizardcloud.sys.service.IUserService;
import com.giantlizardcloud.sys.service.SmsService;
import com.giantlizardcloud.vo.UserDetailsWithRoleAndShopVo;
import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private IUserDetailsService detailsService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SmsService smsService;

    @GetMapping
    public JSONResult get(){
        List<User> list = userService.list();
        return JSONResult.ok(list);
    }

    @GetMapping("test")
    @SysLoginLog
    public JSONResult test(){
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes())
                .getRequest();
        String ipAdrress = IpUtil.getIpAdrress(request);
        log.info(ipAdrress);
        JSONObject forObject = restTemplate.getForObject("http://apis.juhe.cn/ip/ipNew?ip="+ipAdrress+"&key=1f99e2eee91daeef79a39f173650d6da" , JSONObject.class);
        return JSONResult.ok(forObject);
    }

    @GetMapping("/error")
    public JSONResult getError(){
        return JSONResult.errorMsg("错误");
    }

    @GetMapping("/verify/{phoneNumber}")
    public JSONResult VerifyMobileNumber(@PathVariable String phoneNumber) throws ClientException {
        UserDetails userDetails = detailsService.getOne(new QueryWrapper<UserDetails>().eq("user_details_tel", phoneNumber));
        if (userDetails!=null){
            log.info("执行不为空");
            String uuid = "verify-code:"+ UUID.randomUUID().toString();
            int result = (int)((Math.random()*9+1)*100000);
            redisOperator.set(uuid,result,300);
//            smsService.sendSms(phoneNumber);
            return JSONResult.ok(uuid);
        }
        return JSONResult.errorNofind("当前手机号未注册");
    }

    @PostMapping("/verify/code")
    public JSONResult VerifyCode(VerifyCodeDto codeDto){
        log.info(codeDto.toString());
        if (!redisOperator.exists(codeDto.getUuid())) {
            return JSONResult.redisExistsError();
        }
        if (!(redisOperator.getInt(codeDto.getUuid()) == codeDto.getResult())){
            return JSONResult.redisResultError();
        }
        return JSONResult.ok();
    }

    @PostMapping("/verify/change")
    public JSONResult changePassword(ChangePasswordDto dto){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(dto.getPassword());
        log.info(encode);
        detailsService.updateUserPassword(dto.getPhoneNumber(),encode);
        return JSONResult.ok();
    }

    @ApiOperation("获取验证码")
    @GetMapping(value = "/code")
    public ResponseEntity<Object> getCode(){
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();
        String uuid = "loginCode:"+ UUID.randomUUID().toString();
        // 保存
        redisOperator.set(uuid, result,120);
        Map<String,Object> imgResult = new HashMap<String,Object>(2){{
            put("status",200);
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }

    @GetMapping("/all/{page}/{size}")
    public JSONResult getAllUser(@PathVariable Integer page,@PathVariable Integer size){
        Page<UserDetailsWithRoleAndShopVo> voPage = new Page<>(page, size);
        IPage<UserDetailsWithRoleAndShopVo> users=userService.getAllUser(voPage);
        return JSONResult.ok(users);
    }

    @GetMapping("/{query}/{page}/{size}")
    public JSONResult getUserByName(@PathVariable String query,@PathVariable Integer page,@PathVariable Integer size){
        log.info(query);
        Page<UserDetailsWithRoleAndShopVo> voPage = new Page<>(page, size);
        IPage<UserDetailsWithRoleAndShopVo> users=userService.getUserByName(query,voPage);
        return JSONResult.ok(users);
    }

    @PostMapping
    public JSONResult insertUser(InsertUserDto dto){
        log.info(dto.toString());
        userService.insertUser(dto);
        return JSONResult.ok();
    }

    @PutMapping("/start/{id}")
    public JSONResult updateUserStatusByStart(@PathVariable Long id){
        log.info(""+id);
        userService.update(new UpdateWrapper<User>().set("status","NORMAL").eq("user_id",id));
        return JSONResult.ok("已启用");
    }

    @PutMapping("/stop/{id}")
    public JSONResult updateUserStatusByStop(@PathVariable Long id){
        log.info(""+id);
        userService.update(new UpdateWrapper<User>().set("status","PROHIBIT").eq("user_id",id));
        return JSONResult.ok("已停用");
    }

    @PutMapping("/")
    public JSONResult updateUser(UpdateUserDto dto){
        log.info(dto.toString());

        userService.updateUser(dto);
        return JSONResult.ok("修改完成");
    }

    @DeleteMapping("/{uid}")
    public JSONResult deleteUser(@PathVariable Long uid){
        log.info(String.valueOf(uid));
        return JSONResult.ok();
    }
}
