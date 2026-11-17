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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                              @RequestParam("sizee")Long size,
                              @RequestParam(value = "title",required = false)String title,
                              @RequestParam(value = "status",required = false)Integer status) {
        Page<ProdTag> page = new Page<>(current, size);
        page = prodTagService.page(page, new LambdaQueryWrapper<ProdTag>()
                .eq(ObjectUtils.isNotNull(status), ProdTag::getStatus, status)
                .like(StringUtils.hasText(title), ProdTag::getTitle, title)
                .orderByDesc(ProdTag::getSeq));


        return Result.success(page);
    }
}
