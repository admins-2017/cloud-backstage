package com.giantlizardcloud.config.aspect;

import com.alibaba.fastjson.JSONObject;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.sys.entity.LoginRecord;
import com.giantlizardcloud.sys.entity.OperationRecord;
import com.giantlizardcloud.sys.service.ILoginRecordService;
import com.giantlizardcloud.sys.service.IOperationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 获取登录记录
 * @author kang
 * @date 2019.11.19
 */
@Aspect
@Component
@Slf4j
public class OperationRecordAspect {

    private final ILoginRecordService recordService;

    private final RestTemplate restTemplate;

    private final IOperationRecordService operationRecordService;

    public OperationRecordAspect(ILoginRecordService recordService, RestTemplate restTemplate, IOperationRecordService operationRecordService) {
        this.recordService = recordService;
        this.restTemplate = restTemplate;
        this.operationRecordService = operationRecordService;
    }

    @Pointcut("@annotation(com.giantlizardcloud.config.aspect.SysLoginLog)")
    public void controllerAspect(){

    }

    @Pointcut("@annotation(com.giantlizardcloud.config.aspect.SysOperationLog)")
    public void operationAspect(){

    }


    /**
     * @Description 前置通知 ，就是在有SysLoginLog注解的接口执行前执行这个doBefore()方法。
     */
     @After("controllerAspect()")
     public void doBefore(JoinPoint joinPoint) {
         try {
         HttpServletRequest request = ((ServletRequestAttributes)
                 RequestContextHolder.getRequestAttributes())
                 .getRequest();
         String ipAdrress = IpUtil.getIpAdrress(request);
         LoginRecord record=new LoginRecord();
         record.setUserId(SecurityUntil.getUserId());
         record.setRecordIp(ipAdrress);
         JSONObject forObject = restTemplate.getForObject("http://apis.juhe.cn/ip/ipNew?ip="+ipAdrress+"&key=1f99e2eee91daeef79a39f173650d6da" , JSONObject.class);
         IpJsonEntity ipJsonEntity = forObject.getJSONObject("result").toJavaObject(IpJsonEntity.class);
         log.info("result1:"+ipJsonEntity);
         if (!"内网IP".equals(ipJsonEntity.getCity())){
           record.setRecordAddr(ipJsonEntity.getCountry()+"|"+ipJsonEntity.getProvince()+"|"+ipJsonEntity.getCity()+"-"+ipJsonEntity.getIsp());
         }else {
           record.setRecordAddr(ipJsonEntity.getCity());
         }
         record.setRecordTime(LocalDateTime.now());
         record.setRecordId(new IdWorker().nextId());
         log.info("record={}",record);
         recordService.save(record);
     } catch (Exception e){
         e.printStackTrace();
     }
}

    @Before("operationAspect()")
    public void doOperation(JoinPoint joinPoint) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            OperationRecord record=new OperationRecord();
            record.setRequestUser(SecurityUntil.getUserName());
            record.setRequestUrl(request.getRequestURL().toString());
            record.setRequestType(request.getMethod());
            record.setRequestTime(LocalDateTime.now());
            record.setRequestMethod( joinPoint.getTarget().getClass().getName()
                    + "." + joinPoint.getSignature().getName());
            record.setDescription(getControllerMethodDescription(joinPoint));
            String ipAddress = IpUtil.getIpAdrress(request);
            JSONObject forObject = restTemplate.getForObject("http://apis.juhe.cn/ip/ipNew?ip="+ipAddress+"&key=1f99e2eee91daeef79a39f173650d6da" , JSONObject.class);
            IpJsonEntity ipJsonEntity = forObject.getJSONObject("result").toJavaObject(IpJsonEntity.class);
            log.info("result1:"+ipJsonEntity);
            if (!"内网IP".equals(ipJsonEntity.getCity())){
                record.setRequestIp(ipJsonEntity.getCountry()+"|"+ipJsonEntity.getProvince()+"|"+ipJsonEntity.getCity()+"-"+ipJsonEntity.getIsp());
            }else {
                record.setRequestIp(ipJsonEntity.getCity());
            }
            log.info("record={}",record);
            log.info("url={}",request.getRequestURL());
            log.info("uri={}",request.getRequestURI());
            operationRecordService.save(record);

        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /***
     * 获取操作信息
     * @param point
     * @return
     */
    public static String getControllerMethodDescription(JoinPoint point) throws Exception {
        // 获取连接点目标类名
        String targetName = point.getTarget().getClass().getName();
        // 获取连接点签名的方法名
        String methodName = point.getSignature().getName();
        //获取连接点参数
        Object[] args = point.getArgs();
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    description = method.getAnnotation(SysOperationLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

}
