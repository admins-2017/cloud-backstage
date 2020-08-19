package com.giantlizardcloud.config.exception;

import com.giantlizardcloud.config.json.JSONResult;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author kang
 * 配置过滤器统一返回异常
 * @date 2020/2/20 21:55
 */
@RestController
public class ErrorController extends BasicErrorController {

    /**
     * 必须实现的一个构造方法
     **/
    public ErrorController() {
        /** new DefaultErrorAttributes(true) 设置为true
         * 解决 getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL)) 无法获取exception类名
         */
        super(new DefaultErrorAttributes(true),new ErrorProperties());
    }

    /**
     * produces 设置返回的数据类型：application/json
     *
     * @param request 请求
     * @return 自定义的返回实体类
     */
    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        System.out.println("------------------"+status.toString());
        // 获取错误信息
        String message = body.get("message").toString();
        for (String key : body.keySet()) {
            System.out.println("key:" + key + ",value:" + body.get(key));
        }

        if (body.get("exception").equals(SignatureException.class.getName()) || body.get("exception").equals(MalformedJwtException.class.getName())){
            return new ResponseEntity<>(JSONResult.filterError("身份令牌有误 请重新登录"), status);
        }
        if (body.get("exception").equals(ExpiredJwtException.class.getName())){
            return new ResponseEntity<>(JSONResult.filterError("登录信息失效 请重新登录"), status);
        }

        return new ResponseEntity<>(JSONResult.filterError(message), status);
    }
}
