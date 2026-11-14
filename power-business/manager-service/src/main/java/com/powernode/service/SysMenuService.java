package com.powernode.service;

import com.powernode.domain.SysMenu;
import com.baomidou.mybatisplus.spring.service.IService;

import java.util.Set;

/**
* @author Administrator
* @description 针对表【sys_menu(菜单管理)】的数据库操作Service
* @createDate 2026-11-11 18:44:30
*/
public interface SysMenuService extends IService<SysMenu> {

    Set<SysMenu> selectMenuPermsByUserId(Long userId);
}
