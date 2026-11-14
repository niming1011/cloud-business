package com.powernode.vo;

import com.powernode.domain.SysMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Schema(description = "菜单和操作权限")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuAndAuth {
    private Set<String> authorities;
    private Set<SysMenu> menuList;
}
