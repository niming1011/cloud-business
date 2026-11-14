package com.powernode.controller;

import com.powernode.domain.SecurityUser;
import com.powernode.domain.SysUser;
import com.powernode.result.Result;
import com.powernode.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("sys/user")
@Tag(name = "系统管理员接口")
@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @GetMapping("info")
    @Operation(description = "查询登录用户信息")
    public Result loadUserInfo(){
        //获取用户id
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId=securityUser.getUserId();
        //根据id来查询用户信息
        SysUser sysUser = sysUserService.getById(userId);
        return Result.success(sysUser);

    }
}
