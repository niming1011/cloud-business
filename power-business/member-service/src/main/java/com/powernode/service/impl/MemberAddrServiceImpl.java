package com.powernode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.powernode.domain.MemberAddr;
import com.powernode.service.MemberAddrService;
import com.powernode.mapper.MemberAddrMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【member_addr(用户配送地址)】的数据库操作Service实现
* @createDate 2026-11-20 15:45:29
*/
@Service
public class MemberAddrServiceImpl extends ServiceImpl<MemberAddrMapper, MemberAddr>
    implements MemberAddrService{

}




