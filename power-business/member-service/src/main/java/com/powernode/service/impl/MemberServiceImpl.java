package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.Member;
import com.powernode.service.MemberService;
import com.powernode.mapper.MemberMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【member(用户表)】的数据库操作Service实现
* @createDate 2026-11-20 15:45:29
*/
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{

}




