package com.powernode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.constant.ManagerConstant;
import com.powernode.domain.SysRole;
import com.powernode.service.SysRoleService;
import com.powernode.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    @Cacheable(key= ManagerConstant.SYS_ROLE_LIST_KEY)
    public List<SysRole> queryAllRoles() {
        List<SysRole> roleList = sysRoleMapper.selectList(new LambdaQueryWrapper<SysRole>()
                .orderByDesc(SysRole::getCreateTime));
        return roleList;
    }
}




