package com.powernode.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.constant.ManagerConstant;
import com.powernode.domain.SecurityUser;
import com.powernode.domain.SysMenu;
import com.powernode.domain.SysRole;
import com.powernode.domain.SysRoleMenu;
import com.powernode.mapper.SysRoleMenuMapper;
import com.powernode.service.SysRoleMenuService;
import com.powernode.service.SysRoleService;
import com.powernode.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【sys_role(角色)】的数据库操作Service实现
* @createDate 2026-11-11 18:44:30
*/
@Service
@CacheConfig(cacheNames = "com.powernode.service.impl.SysRoleServiceImpl")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{
    @Autowired
     private  SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    @Cacheable(key= ManagerConstant.SYS_ROLE_LIST_KEY)
    public List<SysRole> queryAllRoles() {
        List<SysRole> roleList = sysRoleMapper.selectList(new LambdaQueryWrapper<SysRole>()
                .orderByDesc(SysRole::getCreateTime));
        return roleList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key=ManagerConstant.SYS_ROLE_LIST_KEY)
    public boolean saveSysRole(SysRole sysRole) {
        sysRole.setCreateTime(new Date());
        SecurityUser principal = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sysRole.setCreateUserId(principal.getUserId());
        int insert = sysRoleMapper.insert(sysRole);
        if(insert==1){
            List<Long> menuIdList = sysRole.getMenuIdList();
            List<SysRoleMenu> sysRoleMenuList=new ArrayList<>();
            menuIdList.forEach(menuId->{
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenuList.add(sysRoleMenu);
            });
            return sysRoleMenuService.saveBatch(sysRoleMenuList);
        }
        return false;
    }

    @Override
    public SysRole querySysRoleInfo(Long roleId) {
        SysRole sysRole = sysRoleMapper.selectById(roleId);
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, roleId));

        if(CollectionUtil.isNotEmpty(sysRoleMenuList)&&!sysRoleMenuList.isEmpty()) {
            List<Long> sysMenuIdList = sysRoleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
            sysRole.setMenuIdList(sysMenuIdList);
        }
        return sysRole;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key=ManagerConstant.SYS_ROLE_LIST_KEY)
    public boolean modifySysRoleInfo(SysRole sysRole) {
        Long roleId = sysRole.getRoleId();
        int i = sysRoleMapper.updateById(sysRole);
        if(i>=1) {
            sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>()
                    .eq(SysRoleMenu::getRoleId, roleId));
            List<Long> menuIdList = sysRole.getMenuIdList();
            if(CollectionUtil.isNotEmpty(menuIdList)&&!menuIdList.isEmpty()){
                List<SysRoleMenu> sysRoleMenuList=new ArrayList<>();
                menuIdList.forEach(menuId->{
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setMenuId(menuId);
                    sysRoleMenu.setRoleId(sysRole.getRoleId());
                    sysRoleMenuList.add(sysRoleMenu);
                });
                return sysRoleMenuService.saveBatch(sysRoleMenuList);
            }
        }
        return false;
    }
}




