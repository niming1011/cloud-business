package com.powernode.service;

import com.powernode.domain.SysRole;
import com.baomidou.mybatisplus.spring.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2026-11-11 18:44:30
*/
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> queryAllRoles();
}
