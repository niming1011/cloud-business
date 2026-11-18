package com.powernode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.constant.ProductConstant;
import com.powernode.domain.ProdTag;
import com.powernode.service.ProdTagService;
import com.powernode.mapper.ProdTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【prod_tag(商品分组表)】的数据库操作Service实现
* @createDate 2026-11-16 14:02:23
*/
@Service
@CacheConfig(cacheNames = "com.powernode.service.impl.ProdTagServiceImpl")
public class ProdTagServiceImpl extends ServiceImpl<ProdTagMapper, ProdTag>
    implements ProdTagService{
    @Autowired
    private ProdTagMapper prodTagMapper;

    @Override
    @CacheEvict(key=ProductConstant.PROD_TAG_NORMAL_KEY)
    public boolean saveProdTag(ProdTag prodTag) {
        prodTag.setCreateTime(new Date());
        prodTag.setUpdateTime(new Date());
        return prodTagMapper.insert(prodTag)>=1;
    }

    @Override
    @CacheEvict(key=ProductConstant.PROD_TAG_NORMAL_KEY)
    public boolean modifyProdTag(ProdTag prodTag) {
        prodTag.setUpdateTime(new Date());
        return prodTagMapper.updateById(prodTag)>=1;
    }

    @Override
    @CacheEvict(key=ProductConstant.PROD_TAG_NORMAL_KEY)
    public boolean removeProdTag(Long tagId) {
        int i = prodTagMapper.deleteById(tagId);
        return i>=1;
    }

    @Override
    @Cacheable(key = ProductConstant.PROD_TAG_NORMAL_KEY)
    public List<ProdTag> queryProdTagList() {
        List<ProdTag> prodTagList = prodTagMapper.selectList(new LambdaQueryWrapper<ProdTag>()
                .eq(ProdTag::getStatus, 1)
                .orderByDesc(ProdTag::getCreateTime));
        return prodTagList;
    }
}




