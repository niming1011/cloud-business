package com.powernode.controller;

import com.powernode.domain.Category;
import com.powernode.result.Result;
import com.powernode.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("prod/category")
@Tag(name="商品种类接口")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("table")
    @PreAuthorize("hasAuthority('prod:category:page')")
    @Operation(description = "查询系统所有商品种类")
    public Result loadAllCategoryList() {
        List<Category> categoryList=categoryService.queryAllCategoryList();
        return Result.success(categoryList);
    }
}
