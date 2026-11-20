package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.Notice;
import com.powernode.service.NoticeService;
import com.powernode.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author Administrator
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2026-11-18 16:59:56
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public boolean saveNotice(Notice notice) {
        notice.setCreateTime(new Date());
        notice.setUpdateTime(new Date());
        notice.setShopId(1L);

        return noticeMapper.insert(notice)>=1;
    }

    @Override
    public boolean modifyNotice(Notice notice) {
        notice.setUpdateTime(new Date());
        return noticeMapper.updateById(notice)>=1;
    }
}




