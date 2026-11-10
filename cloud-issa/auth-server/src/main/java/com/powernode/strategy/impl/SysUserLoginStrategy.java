package com.powernode.strategy.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.powernode.domain.LoginSysUser;
import com.powernode.mapper.LoginSysUserMapper;
import com.powernode.model.SecurityUser;
import com.powernode.strategy.LoginStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("sysUserLogin")
public class SysUserLoginStrategy implements LoginStrategy {
    @Autowired
    private LoginSysUserMapper loginSysUserMapper;

    @Override
    public UserDetails realLogin(String username) {
        LoginSysUser loginSysUser = loginSysUserMapper.selectOne(new LambdaQueryWrapper<LoginSysUser>().eq(LoginSysUser::getUsername, username));
        SecurityUser securityUser = new SecurityUser();


        if(!ObjectUtil.isEmpty(loginSysUser)){
            Set<String> perms = loginSysUserMapper.selectPermsByUserId(loginSysUser.getUserId());
            securityUser.setLoginType("sysUserLogin");
            securityUser.setPassword(loginSysUser.getPassword());
            securityUser.setShopId(loginSysUser.getShopId());
            securityUser.setUserId(loginSysUser.getUserId());
            securityUser.setStatus(loginSysUser.getStatus());
            if(!CollectionUtil.isEmpty(perms)){
                securityUser.setPerms(perms);
            }
            return securityUser;
        }
      return null;
    }
}
