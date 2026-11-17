package com.powernode.controller;

import com.powernode.domain.Category;
import com.powernode.result.Result;
import com.powernode.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("listCategory")
    @PreAuthorize("hasAuthority('prod:category:page')")
    @Operation(description = "查询商品一级类目")
    public Result loadFirstCategoryList() {
        List<Category> firstCategoryList=categoryService.queryFirstCategoryList();
        return Result.success(firstCategoryList);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('prod:category:save')")
    @Operation(description = "新增商品类目")
    public Result saveCategory(@RequestBody Category category) {
        boolean save=categoryService.saveCategory(category);
        return Result.handle(save);
    }

    @GetMapping("info/{categoryId}")
    @PreAuthorize("hasAuthority('prod:category:info')")
    @Operation(description = "根据标识查询商品类目详情")
    public Result queryCategoryInfo(@PathVariable Long categoryId) {
        Category byId = categoryService.getById(categoryId);
        return Result.success(byId);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('prod:category:update')")
    @Operation(description = "修改类目信息")
    public Result modifyCategory(@RequestBody Category category) {
        boolean modified=categoryService.modifyCategory(category);
        return Result.handle(modified);
    }
}
