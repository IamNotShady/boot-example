package com.boot.common.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 日志切片
 */
@Aspect
@Service
public class LoggerManage {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    @Pointcut("execution(* com.boot..*.*(..))")
//    public void webLog(){}

    @Before("within(com.boot..*) && @annotation(loggerAnnotation)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerAnnotation loggerAnnotation) {
        log.info("执行 " + loggerAnnotation.description() + " 开始");
//        log.info("方法路径:"+joinPoint.getSignature().toString());
        log.info("传入参数:" + parseParames(joinPoint.getArgs()));
    }

    @AfterReturning("within(com.boot..*) && @annotation(loggerAnnotation)")
    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerAnnotation loggerAnnotation) {
        log.info("执行 " + loggerAnnotation.description() + " 结束");
    }

//    @AfterThrowing(pointcut = "within(com.boot..*) && @annotation(loggerAnnotation)", throwing = "e")
//    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerAnnotation loggerAnnotation, Exception e) {
//        log.error(e.getMessage(),e);
//    }

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0) {
            return "";
        }
        StringBuffer param = new StringBuffer();
        for (Object obj : parames) {
            param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
        }
        return param.toString();
    }

}
