package com.powernode.service;

import com.powernode.domain.IndexImg;
import com.baomidou.mybatisplus.spring.service.IService;

/**
* @author Administrator
* @description 针对表【index_img(主页轮播图)】的数据库操作Service
* @createDate 2026-11-18 16:59:56
*/
public interface IndexImgService extends IService<IndexImg> {

    boolean saveIndexImg(IndexImg indexImg);
}
