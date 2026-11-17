package com.powernode.service;

import com.powernode.domain.Category;
import com.baomidou.mybatisplus.spring.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【category(产品类目)】的数据库操作Service
* @createDate 2026-11-16 14:02:23
*/
public interface CategoryService extends IService<Category> {

    List<Category> queryAllCategoryList();

    List<Category> queryFirstCategoryList();

    boolean saveCategory(Category category);

    boolean modifyCategory(Category category);

    boolean removeCategory(Long categoryId);
}
