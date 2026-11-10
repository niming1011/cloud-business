package com.powernode.handler;

import cn.hutool.json.JSONUtil;
import com.powernode.constant.AuthConstants;
import com.powernode.model.LoginResult;
import com.powernode.model.SecurityUser;
import com.powernode.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.UUID;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        SecurityUser principal = (SecurityUser)authentication.getPrincipal();
        String token= UUID.randomUUID().toString();
        String jsonStr = JSONUtil.toJsonStr(principal);
        redisTemplate.opsForValue().set(AuthConstants.LOGIN_TOKEN_PREFIX+token,jsonStr, Duration.ofSeconds(4*60*60));
        LoginResult loginResult = new LoginResult(token,4*60*60L);
        Result success = Result.success(loginResult);
        String successJson = JSONUtil.toJsonStr(success);
        PrintWriter writer = response.getWriter();
        writer.write(successJson);
        writer.flush();
        writer.close();
    }
}
