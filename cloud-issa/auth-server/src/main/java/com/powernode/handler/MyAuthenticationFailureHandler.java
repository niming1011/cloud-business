package com.powernode.handler;

import cn.hutool.json.JSONUtil;
import com.powernode.constant.AuthConstants;
import com.powernode.constant.BusinessEnum;
import com.powernode.model.LoginResult;
import com.powernode.model.SecurityUser;
import com.powernode.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.UUID;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        Result fail = Result.builder().code(BusinessEnum.OPERATION_FAIL.getCode()).build();
        if(exception instanceof BadCredentialsException){
            fail.setMsg("用户名或密码输入错误");
        }else if(exception instanceof UsernameNotFoundException){
            fail.setMsg("用户名不存在");
        }else if(exception instanceof AccountExpiredException){
            fail.setMsg("账号异常，请联系管理员");
        }else if(exception instanceof AccountStatusException){
            fail.setMsg("账号异常，请联系管理员");
        }else if(exception instanceof InternalAuthenticationServiceException){
            fail.setMsg(exception.getMessage());
        }else{
            fail.setMsg(BusinessEnum.OPERATION_FAIL.getDesc());
        }
        String failJson = JSONUtil.toJsonStr(fail);
        PrintWriter writer = response.getWriter();
        writer.write(failJson);
        writer.flush();
        writer.close();
    }
}
