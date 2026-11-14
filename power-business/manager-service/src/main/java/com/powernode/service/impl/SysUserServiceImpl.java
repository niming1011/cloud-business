package com.powernode.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.SecurityUser;
import com.powernode.domain.SysUser;
import com.powernode.domain.SysUserRole;
import com.powernode.service.SysUserRoleService;
import com.powernode.service.SysUserService;
import com.powernode.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【sys_user(系统用户)】的数据库操作Service实现
* @createDate 2026-11-11 18:44:30
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSysUser(SysUser sysUser) {
        SecurityUser principal = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sysUser.setCreateTime(new Date());
        sysUser.setCreateUserId(principal.getUserId());
        sysUser.setShopId(1L);
        int insert = sysUserMapper.insert(sysUser);
        if (insert == 1) {
            Long userId = sysUser.getUserId();
            List<Long> roleIdList = sysUser.getRoleIdList();
            if (!CollectionUtil.isEmpty(roleIdList) && !roleIdList.isEmpty()) {
                List<SysUserRole> sysUserRoles = new ArrayList<>();
                roleIdList.forEach(roleId -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setRoleId(roleId);
                    sysUserRole.setUserId(userId);
                    sysUserRoles.add(sysUserRole);
                });
                return sysUserRoleService.saveBatch(sysUserRoles);
            }
        }
        return  false;
    }
}




