package com.powernode.controller;


import com.powernode.domain.SysRole;
import com.powernode.result.Result;
import com.powernode.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
