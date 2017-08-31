package com.wtyt.pub.aop;

import java.lang.annotation.*;

/**
 * 日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerAnnotation {

    public String description();

}
