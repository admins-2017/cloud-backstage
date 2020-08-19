package com.giantlizardcloud.sys.service;


import com.aliyuncs.exceptions.ClientException;

import java.util.Map;

public interface SmsService {

     Map<String,Object> sendSms(String number) throws ClientException;
}
