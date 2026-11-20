package com.powernode.service;

import com.powernode.domain.Notice;
import com.baomidou.mybatisplus.spring.service.IService;

/**
* @author Administrator
* @description 针对表【notice】的数据库操作Service
* @createDate 2026-11-18 16:59:56
*/
public interface NoticeService extends IService<Notice> {

    boolean saveNotice(Notice notice);
}
