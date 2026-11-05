package com.powernode.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SysMenu {
    @Schema(description="")
    private Long menuId;
    @Schema(description="父菜单ID，一级菜单为0")
    private Long parentId;
    @Schema(description="菜单名称")
    private String name;
    @Schema(description="菜单URL")
    private String url;
    @Schema(description="授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;
    @Schema(description="类型   0：目录   1：菜单   2：按钮")
    private Integer type;
    @Schema(description="菜单图标")
    private String icon;
    @Schema(description="排序")
    private Integer orderNum;
}
