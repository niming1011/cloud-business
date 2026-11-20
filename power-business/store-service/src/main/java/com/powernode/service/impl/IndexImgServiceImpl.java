package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.IndexImg;
import com.powernode.service.IndexImgService;
import com.powernode.mapper.IndexImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author Administrator
* @description 针对表【index_img(主页轮播图)】的数据库操作Service实现
* @createDate 2026-11-18 16:59:56
*/
@Service
public class IndexImgServiceImpl extends ServiceImpl<IndexImgMapper, IndexImg>
    implements IndexImgService{
    @Autowired
    private IndexImgMapper indexImgMapper;

    @Override
    public boolean saveIndexImg(IndexImg indexImg) {
        indexImg.setShopId(1L);
        indexImg.setCreateTime(new Date());
        Integer type = indexImg.getType();
        if(-1==type){
            indexImg.setProdId(-1L);
        }
        int insert = indexImgMapper.insert(indexImg);
        return insert>=1;
    }
}




