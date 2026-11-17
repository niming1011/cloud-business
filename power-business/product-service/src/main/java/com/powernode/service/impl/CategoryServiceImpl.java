package com.powernode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.constant.ProductConstant;
import com.powernode.domain.Category;
import com.powernode.exception.BusinessException;
import com.powernode.service.CategoryService;
import com.powernode.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【category(产品类目)】的数据库操作Service实现
* @createDate 2026-11-16 14:02:23
*/
@Service
@CacheConfig(cacheNames = "com.powernode.service.impl.CategoryServiceImpl")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    @Cacheable(key= ProductConstant.ALL_CATEGORY_LIST_KEY)
    public List<Category> queryAllCategoryList() {
        return categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .orderByDesc(Category::getSeq));
    }

    @Override
    @Cacheable(key=ProductConstant.FIRST_CATEGORY_LIST_KEY)
    public List<Category> queryFirstCategoryList() {
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .eq(Category::getParentId, 0L)
                        .eq(Category::getStatus,1)
                .orderByDesc(Category::getSeq));
        return categoryList;
    }

    @Override
    @Caching(
            evict ={ @CacheEvict(key=ProductConstant.FIRST_CATEGORY_LIST_KEY),
            @CacheEvict(key=ProductConstant.ALL_CATEGORY_LIST_KEY)}

    )
    public boolean saveCategory(Category category) {
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        int insert = categoryMapper.insert(category);
        return insert>=1;
    }

    @Override
    @Caching(
            evict ={ @CacheEvict(key=ProductConstant.FIRST_CATEGORY_LIST_KEY),
                    @CacheEvict(key=ProductConstant.ALL_CATEGORY_LIST_KEY)}
    )
    public boolean modifyCategory(Category category) {
        Long categoryId = category.getCategoryId();
        Long afterParentId = category.getParentId();
        Category afterCategory = categoryMapper.selectById(categoryId);
        Long beforeParentId = afterCategory.getParentId();
        //如果一级类目要变二级类目
        if(beforeParentId==0&&afterParentId!=null &&afterParentId!=0){
            List<Category> childList = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                    .eq(Category::getParentId, categoryId));
            if(CollectionUtils.isNotEmpty(childList)){
                throw new BusinessException("该类目下有子类目，无法进行删除");
            }
        }
        //二级类目变一级类目
        if(beforeParentId==1&&afterParentId==null){
            category.setParentId(0L);
        }
        return categoryMapper.updateById(category)>=1;
    }

    @Override
    @Caching(
            evict ={ @CacheEvict(key=ProductConstant.FIRST_CATEGORY_LIST_KEY),
                    @CacheEvict(key=ProductConstant.ALL_CATEGORY_LIST_KEY)}
    )
    public boolean removeCategory(Long categoryId) {
        List<Category> childCategoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .eq(Category::getParentId, categoryId));
        if(CollectionUtils.isNotEmpty(childCategoryList)){
            throw new BusinessException("该类目存在二级类目，不允许删除");
        }

        return categoryMapper.deleteById(categoryId)>=1;
    }
}




