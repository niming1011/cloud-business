package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.Notice;
import com.powernode.service.NoticeService;
import com.powernode.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2026-11-18 16:59:56
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

}




