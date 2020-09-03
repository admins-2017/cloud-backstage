package com.giantlizardcloud.config.aspect;

import java.lang.annotation.*;

/**
 * 用户操作记录
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysOperationLog {

    String description() default "";

}
