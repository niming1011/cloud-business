package com.powernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.Prod;
import com.powernode.result.Result;
import com.powernode.service.ProdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("prod/prod")
@Tag(name = "商品接口管理")
@RestController
public class ProdController {
    @Autowired
    private ProdService prodService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('prod:prod:page')")
    @Operation(description = "多条件分页查询商品西信息")
    public Result loadProdInfo(@RequestParam("current") Long current,
                               @RequestParam("size") Long size,
                               @RequestParam(value = "prodName",required = false)String prodName,
                               @RequestParam(value = "status",required = false)Integer status) {
        Page<Prod> page = new Page<>(current,size);
        page = prodService.page(page, new LambdaQueryWrapper<Prod>()
                .eq(ObjectUtils.isNotNull(status), Prod::getStatus, status)
                .like(StringUtils.hasText(prodName), Prod::getProdName, prodName)
                .orderByDesc(Prod::getCreateTime));
        return Result.success(page);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('prod:prod:save')")
    @Operation(description = "新增商品")
    public Result saveProd(@RequestBody Prod prod) {
        boolean save=prodService.saveProd(prod);
        return Result.handle(save);
    }
}
