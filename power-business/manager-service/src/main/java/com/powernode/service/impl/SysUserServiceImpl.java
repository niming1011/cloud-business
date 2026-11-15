package com.powernode.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.SecurityUser;
import com.powernode.domain.SysUser;
import com.powernode.domain.SysUserRole;
import com.powernode.mapper.SysUserRoleMapper;
import com.powernode.service.SysUserRoleService;
import com.powernode.service.SysUserService;
import com.powernode.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSysUser(SysUser sysUser) {
        SecurityUser principal = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sysUser.setCreateTime(new Date());
        sysUser.setCreateUserId(principal.getUserId());
        sysUser.setShopId(1L);
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
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

    @Override
    public SysUser querySysUserInfoById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, id));
        if(CollectionUtil.isNotEmpty(sysUserRoles)&&!sysUserRoles.isEmpty()){
            List<Long> roleIdList = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
            sysUser.setRoleIdList(roleIdList);
        }

        return sysUser;
    }

    @Override
    @Transactional
    public boolean modifySysUserInfo(SysUser sysUser) {
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, sysUser.getUserId()));
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        sysUser.getRoleIdList().forEach(roleId->{
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(sysUser.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoles.add(sysUserRole);
        });
        boolean saveBatch = sysUserRoleService.saveBatch(sysUserRoles);
        String password=sysUser.getPassword();
        if(StringUtils.hasText(password)) {
            String encode = bCryptPasswordEncoder.encode(password);
            sysUser.setPassword(encode);
        }else{
            sysUser.setPassword(null);
        }
        int updateById = sysUserMapper.updateById(sysUser);
        return saveBatch&&updateById==1;
    }

    @Override
    public boolean removeSysUserByIds(List<Long> ids) {
        boolean remove = sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>()
                .in(SysUserRole::getUserId, ids));
        int deleteByIds = sysUserMapper.deleteByIds(ids);
        return remove&&deleteByIds>=1;

    }
}




