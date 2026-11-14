package com.powernode.controller;

import com.powernode.domain.SecurityUser;
import com.powernode.domain.SysMenu;
import com.powernode.result.Result;
import com.powernode.service.SysMenuService;
import com.powernode.vo.MenuAndAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Tag(name="系统权限接口管理")
@RequestMapping("sys/menu")
@RestController
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
    @Operation(summary = "查询用户的操作权限和菜单权限")
    @GetMapping("nav")
    public Result loadUserMenuAndAuth(){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = securityUser.getUserId();
        //根据userId获取操作权限
        Set<String> perms = securityUser.getPerms();

        //根据userId获取菜单权限
        Set<SysMenu> sysMenus = sysMenuService.selectMenuPermsByUserId(userId);
        MenuAndAuth menuAndAuth = new MenuAndAuth(perms, sysMenus);
        Result success = Result.success(menuAndAuth);
        return success;
    }
}
