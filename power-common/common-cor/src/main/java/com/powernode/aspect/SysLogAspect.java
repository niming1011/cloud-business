package com.powernode.aspect;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.powernode.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
@Slf4j
public class SysLogAspect  {
    public static final String POINT_CUT="execution (* com.powernode.controller.*.*(..))";

    @Around(value = POINT_CUT)
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint){
        Object result=null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String path = request.getRequestURI();
        String remoteHost = request.getRemoteHost();
        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        Operation declaredAnnotation = method.getDeclaredAnnotation(Operation.class);
        String description=null;
        if(ObjectUtil.isNotNull(declaredAnnotation)){
            description = declaredAnnotation.description();
        }
        Object[] args = proceedingJoinPoint.getArgs();
        String finalArgs=null;
        if(ObjectUtil.isNotEmpty(args)&&args[0] instanceof MultipartFile){
            finalArgs="file";
        }else{
            finalArgs= JSONUtil.toJsonStr(args);
        }
        Long startTime=System.currentTimeMillis();

        try {
            result=proceedingJoinPoint.proceed(args);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        Long endTime=System.currentTimeMillis();
        Long execTime=endTime-startTime;
        Date invokeTime = new Date();
        log.info("调用时间{}，请求接口路径{}，请求地址{}，方法名称{}，执行时间{}，方法描述{}",invokeTime,path,remoteHost,methodName,execTime,description);
        return result;
    }
}
