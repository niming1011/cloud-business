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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @PreAuthorize("hasAuthority('sys:role:save')")
    @Operation(description = "新增角色")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        boolean save=sysRoleService.saveSysRole(sysRole);
        return Result.handle(save);
    }

    @GetMapping("info/{roleId}")
    @PreAuthorize("hasAuthority('sys:role:info')")
    @Operation(description = "根据标识查询角色详情")
    public Result querySysRoleInfo(@PathVariable Long roleId) {
        SysRole sysRole=sysRoleService.querySysRoleInfo(roleId);
        return Result.success(sysRole);
    }
    @PutMapping
    @PreAuthorize("hasAuthority('sys:role:update')")
    @Operation(description = "修改角色信息")
    public Result modifySysRoleInfo(@RequestBody SysRole sysRole) {
        boolean modify=sysRoleService.modifySysRoleInfo(sysRole);
        return Result.handle(modify);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @Operation(description = "批量/单个删除角色")
    public Result removeSysRoleByIdList(@RequestBody List<Long> sysRoleIds) {
        boolean remove=sysRoleService.removeSysRoleByIds(sysRoleIds);
        return Result.handle(remove);
    }
}
