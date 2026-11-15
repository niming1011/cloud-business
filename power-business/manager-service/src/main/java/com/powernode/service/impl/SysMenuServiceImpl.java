package com.powernode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.constant.ManagerConstant;
import com.powernode.domain.SysMenu;
import com.powernode.service.SysMenuService;
import com.powernode.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    @Cacheable(key= ManagerConstant.SYS_ALL_MENU_KEY)
    public List<SysMenu> queryAllMenuList() {
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(new LambdaQueryWrapper<SysMenu>());
        return sysMenuList;
    }

    @Override
    @CacheEvict(key = ManagerConstant.SYS_ALL_MENU_KEY)
    public boolean saveSysMenu(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu)>=1;
    }

    @Override
    @CacheEvict(key=ManagerConstant.SYS_ALL_MENU_KEY)
    public boolean modifySysMenu(SysMenu sysMenu) {
        if(sysMenu.getType()==0){
            sysMenu.setParentId(0L);
        }
        return sysMenuMapper.updateById(sysMenu)>=1;

    }
}




