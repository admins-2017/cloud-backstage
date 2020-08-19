package com.giantlizardcloud.sys.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.dto.SmsLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private RedisOperator redisOperator;



    @PostMapping("/send")
    public JSONResult sendMessage(SmsLoginDto dto){

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G7chJ3Ye53r6XpRDZmm"
                , "buAXkNxuamoHKoC1DYJSWb0FOE6CR2");
        IAcsClient client = new DefaultAcsClient(profile);
        int result = (int)((Math.random()*9+1)*100000);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", dto.getPhoneNumber());
        request.putQueryParameter("SignName", "巨蜥云");
        request.putQueryParameter("TemplateCode", "SMS_198917512");
        request.putQueryParameter("TemplateParam", JSON.toJSONString(JSONResult.map(result)));
        try {
            CommonResponse response = client.getCommonResponse(request);
            String key = "phoneCode:"+ UUID.randomUUID().toString();
            redisOperator.set(key,String.valueOf(result),180);
            Map<String,Object> smsKey = new HashMap<String,Object>(2){{
                put("uuid", key);
            }};
            return JSONResult.sendSmsOk(smsKey,"发送验证码成功");
        } catch (ServerException e) {
            e.printStackTrace();
            return JSONResult.errorMsg(e.getMessage());
        } catch (ClientException e) {
            e.printStackTrace();
            return JSONResult.errorMsg(e.getMessage());
        }
    }
}
