package com.powernode.handler;

import cn.hutool.json.JSONUtil;
import com.powernode.constant.AuthConstants;
import com.powernode.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, @Nullable Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String authorization = request.getHeader(AuthConstants.AUTHORIZATION);
        String token=authorization.replaceFirst(AuthConstants.BEARER,"");
        redisTemplate.delete(AuthConstants.LOGIN_TOKEN_PREFIX+token);
        Result success = Result.success(null);
        String jsonStr = JSONUtil.toJsonStr(success);
        PrintWriter writer = response.getWriter();
        writer.write(jsonStr);
        writer.flush();
        writer.close();

    }
}
