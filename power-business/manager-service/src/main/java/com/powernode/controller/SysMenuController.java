package com.powernode.controller;

import com.powernode.domain.SecurityUser;
import com.powernode.domain.SysMenu;
import com.powernode.result.Result;
import com.powernode.service.SysMenuService;
import com.powernode.vo.MenuAndAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping("table")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    @Operation(description = "查询系统所有权限集合")
    public Result loadAllMenuList(){
       List<SysMenu> sysMenuList= sysMenuService.queryAllMenuList();
        return Result.success(sysMenuList);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:menu:save')")
    @Operation(description = "新增权限")
    public Result saveSysMenu(@RequestBody SysMenu sysMenu) {
        boolean save = sysMenuService.saveSysMenu(sysMenu);
        return Result.handle(save);
    }
    @GetMapping("info/{menuId}")
    @PreAuthorize("hasAuthority('sys:menu:info')")
    @Operation(description = "根据标识查询菜单权限详细信息")
    public Result loadSysMenuInfo(@PathVariable Long menuId) {
        SysMenu byId = sysMenuService.getById(menuId);
        return Result.success(byId);
    }
    @PutMapping
    @PreAuthorize("hasAuthority('sys:menu:update')")
    @Operation(description = "修改菜单权限信息")
    public Result modifySysMenu(@RequestBody SysMenu sysMenu) {
        boolean modify=sysMenuService.modifySysMenu(sysMenu);
        return Result.handle(modify);
    }
}
