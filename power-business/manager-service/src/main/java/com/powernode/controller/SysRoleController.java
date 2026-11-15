package com.powernode.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.SysRole;
import com.powernode.result.Result;
import com.powernode.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("sys/role")
@RestController
@Tag(name = "系统角色接口")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('sys:role:list')")
    @Operation(description = "查询所有角色列表")
    public Result loadSysRoleList() {
        List<SysRole> roleList= sysRoleService.queryAllRoles();

        return Result.success(roleList);
    }
    @GetMapping("page")
    @PreAuthorize("hasAuthority('sys:role:page')")
    @Operation(description = "多条件分页查询角色")
    public Result loadSysRolePage(@RequestParam("current")Long current,
                                  @RequestParam("size") Long size,
                                  @RequestParam(value="roleName",required = false)String roleName){
        Page<SysRole> page = new Page<>(current,size);
        page=sysRoleService.page(page,new LambdaQueryWrapper<SysRole>()
                .like(StringUtils.hasText(roleName),SysRole::getRoleName,roleName));
        return Result.success(page);
    }

}
