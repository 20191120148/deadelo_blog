package com.deadelo.aspect;

import com.alibaba.fastjson.JSON;
import com.deadelo.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/8/7  18:42
 */

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.deadelo.annotation.SystemLog)")
    public void pt(){}

    @Around("pt()")
    public Object pringLog(ProceedingJoinPoint joinPoint) throws Throwable {

        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            log.info("=======End=======" + System.lineSeparator());
        }

        return ret;
    }

    private void handleAfter(Object ret) {
        // 打印出参

        log.info("Response       : {}", JSON.toJSONString(ret));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes requestAttribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttribute.getRequest();

        //获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);


        log.info("=======Start=======");
        // 打印请求 URL

        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}",systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}",request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(),
                ((MethodSignature) joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = signature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }
}
