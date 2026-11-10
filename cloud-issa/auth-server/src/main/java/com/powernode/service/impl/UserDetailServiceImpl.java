package com.powernode.service.impl;

import com.powernode.factory.LoginStrategyFactory;
import com.powernode.strategy.LoginStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private LoginStrategyFactory loginStrategyFactory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String loginType = request.getHeader("loginType");
        //通过策略设计模式来选择对应登录用户类型所对应的登录流程(一个接口，多个实现)
        if(!StringUtils.hasText(loginType)){
            throw new InternalAuthenticationServiceException("非法登录，登录类型错误");
        }
        LoginStrategy loginStrategy = loginStrategyFactory.getInstance(loginType);
        UserDetails userDetails = loginStrategy.realLogin(username);
        
        return userDetails;
    }
}
