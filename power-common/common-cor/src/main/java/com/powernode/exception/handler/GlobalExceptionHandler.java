package com.powernode.exception.handler;

import com.powernode.constant.AuthConstants;
import com.powernode.constant.BusinessEnum;
import com.powernode.exception.BusinessException;
import com.powernode.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements Serializable {
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException e){
        log.error(e.getMessage());
        return Result.fail(BusinessEnum.SERVER_INNER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result accessDeniedExceptionHandler(AccessDeniedException e){
        log.error(e.getMessage());
        throw e;
    }
    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(BusinessException e){
        log.error(e.getMessage());
        return Result.fail(BusinessEnum.OPERATION_FAIL.getCode(),e.getMessage());
    }

}
