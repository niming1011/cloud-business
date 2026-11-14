package com.powernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.SecurityUser;
import com.powernode.domain.SysUser;
import com.powernode.result.Result;
import com.powernode.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("page")
    @PreAuthorize("hasAuthority('sys:user:page')")
    @Operation(description = "多条件分页查询系统管理员")
    public Result loadSysUserPage(@RequestParam("current") Long current,
                                  @RequestParam("size") Long size,
                                  @RequestParam(value = "username",required = false) String username){
        Page<SysUser> sysUserPage = new Page<>(current,size);
        sysUserPage=sysUserService.page(sysUserPage,new LambdaQueryWrapper<SysUser>()
                .like(StringUtils.hasText(username),SysUser::getUsername,username)
                .orderByDesc(SysUser::getCreateTime));


        return Result.success(sysUserPage);

    }
        @PostMapping
        @PreAuthorize("hasAuthority('sys:user:save')")
        @Operation(description = "新增管理员")
        public Result saveSysUser(@RequestBody SysUser sysUser) {
            boolean save = sysUserService.saveSysUser(sysUser);
            return Result.handle(save);
        }
}
