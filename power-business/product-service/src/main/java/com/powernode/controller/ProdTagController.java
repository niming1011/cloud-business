package com.powernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.ProdTag;
import com.powernode.result.Result;
import com.powernode.service.ProdTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("prod/prodTag")
@Tag(name="分组标签接口管理")
@RestController
public class ProdTagController {
    @Autowired
    private ProdTagService prodTagService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('prod:prodTag:page')")
    @Operation(description = "多条件分页查询分组标签")
    public Result loadProdTag(@RequestParam("current")Long current,
                              @RequestParam("size")Long size,
                              @RequestParam(value = "title",required = false)String title,
                              @RequestParam(value = "status",required = false)Integer status) {
        Page<ProdTag> page = new Page<>(current, size);
        page = prodTagService.page(page, new LambdaQueryWrapper<ProdTag>()
                .eq(ObjectUtils.isNotNull(status), ProdTag::getStatus, status)
                .like(StringUtils.hasText(title), ProdTag::getTitle, title)
                .orderByDesc(ProdTag::getSeq));


        return Result.success(page);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('prod:prodTag:save')")
    @Operation(description = "新增分组标签")
    public Result saveProdTag(@RequestBody ProdTag prodTag) {
        boolean save=prodTagService.saveProdTag(prodTag);
        return Result.handle(save);
    }

    @GetMapping("info/{tagId}")
    @PreAuthorize("hasAuthority('prod:prodTag:info')")
    @Operation(description = "根据标识查询分组标签详情")
    public Result loadProdTagInfo(@PathVariable Long tagId) {
        ProdTag byId = prodTagService.getById(tagId);
        return Result.success(byId);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('prod:prodTag:update')")
    @Operation(description = "修改分组标签信息")
    public Result modifyProdTag(@RequestBody ProdTag prodTag) {
       boolean modified= prodTagService.modifyProdTag(prodTag);
        return Result.handle(modified);
    }

    @DeleteMapping("{tagId}")
    @PreAuthorize("hasAuthority('prod:prodTag:delete')")
    @Operation(description = "根据标识删除商品分组标签")
    public Result removeProdTag(@PathVariable Long tagId) {
        boolean removed = prodTagService.removeProdTag(tagId);
        return Result.handle(removed);
    }

    @GetMapping("listTagList")
    @PreAuthorize("hasAuthority('prod:prodTag:page')")
    @Operation(description = "查询状态正常的商品分组标签集合")
    public Result loadProdTagList() {
        List<ProdTag> prodTagList=prodTagService.queryProdTagList();
        return Result.success(prodTagList);
    }
}
