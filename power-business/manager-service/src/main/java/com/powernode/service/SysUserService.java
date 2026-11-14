package com.powernode.service;

import com.powernode.domain.SysUser;
import com.baomidou.mybatisplus.spring.service.IService;

/**
* @author Administrator
* @description 针对表【sys_user(系统用户)】的数据库操作Service
* @createDate 2026-11-11 18:44:30
*/
public interface SysUserService extends IService<SysUser> {

    boolean saveSysUser(SysUser sysUser);
}
