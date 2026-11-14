package com.powernode.filter;

import cn.hutool.json.JSONUtil;
import com.powernode.constant.AuthConstants;
import com.powernode.domain.SecurityUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class TokenTranslationFilter extends OncePerRequestFilter {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AuthConstants.AUTHORIZATION);
        if(StringUtils.hasText(authorization)){
            String token = authorization.replaceFirst(AuthConstants.BEARER, "");
            if(StringUtils.hasText(token)){
                String json = redisTemplate.opsForValue().get(AuthConstants.LOGIN_TOKEN_PREFIX + token);
                Long expire = redisTemplate.getExpire(AuthConstants.LOGIN_TOKEN_PREFIX + token, TimeUnit.SECONDS);
                if(expire<3600){
                    redisTemplate.expire(AuthConstants.LOGIN_TOKEN_PREFIX+token, Duration.ofSeconds(4*60*60L));
                }
                SecurityUser securityUser = JSONUtil.toBean(json, SecurityUser.class);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(securityUser,null, securityUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
