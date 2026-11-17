package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.ProdTag;
import com.powernode.service.ProdTagService;
import com.powernode.mapper.ProdTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author Administrator
* @description 针对表【prod_tag(商品分组表)】的数据库操作Service实现
* @createDate 2026-11-16 14:02:23
*/
@Service
public class ProdTagServiceImpl extends ServiceImpl<ProdTagMapper, ProdTag>
    implements ProdTagService{
    @Autowired
    private ProdTagMapper prodTagMapper;

    @Override
    public boolean saveProdTag(ProdTag prodTag) {
        prodTag.setCreateTime(new Date());
        prodTag.setUpdateTime(new Date());
        return prodTagMapper.insert(prodTag)>=1;
    }

    @Override
    public boolean modifyProdTag(ProdTag prodTag) {
        prodTag.setUpdateTime(new Date());
        return prodTagMapper.updateById(prodTag)>=1;
    }
}




