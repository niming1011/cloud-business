package com.powernode.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SysRoleMenu {
    @Schema(description="")
    private Long id;
    @Schema(description="角色ID")
    private Long roleId;
    @Schema(description="菜单ID")
    private Long menuId;
}
