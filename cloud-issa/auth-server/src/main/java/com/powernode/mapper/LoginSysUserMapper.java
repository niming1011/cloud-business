package com.powernode.mapper;

import com.powernode.domain.LoginSysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
* @author Administrator
* @description 针对表【sys_user(系统用户)】的数据库操作Mapper
* @createDate 2026-11-09 11:17:27
* @Entity com.powernode.domain.SysUser
*/
@Mapper
public interface LoginSysUserMapper extends BaseMapper<LoginSysUser> {

    Set<String> selectPermsByUserId(Long userId);
}




