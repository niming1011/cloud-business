package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.SysMenu;
import com.powernode.service.SysMenuService;
import com.powernode.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【sys_menu(菜单管理)】的数据库操作Service实现
* @createDate 2026-11-11 18:44:30
*/
@Service
@CacheConfig(cacheNames = "com.powernode.service.impl.SysMenuServiceImpl")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    @Cacheable(key="#userId")
    public Set<SysMenu> selectMenuPermsByUserId(Long userId) {
        Set<SysMenu> sysMenus = sysMenuMapper.selectMenuPermsByUserId(userId);
        return transformTree(sysMenus,0L);

    }

    private Set<SysMenu> transformTree(Set<SysMenu> sysMenus,Long pid) {
        Set<SysMenu> root = sysMenus.stream().filter(m -> m.getParentId().equals(pid)).collect(Collectors.toSet());
        root.forEach(r->r.setList(transformTree(sysMenus,r.getMenuId())));
        return root;
    }
}




