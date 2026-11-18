package com.powernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.ProdComm;
import com.powernode.result.Result;
import com.powernode.service.ProdCommService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("prod/prodComm")
@Tag(name="商品评论接口管理")
@RestController
public class ProdCommController {
    @Autowired
    private ProdCommService prodCommService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('prod:prodComm:page')")
    @Operation(description = "多条件分页查询商品评论")
    public Result loadProdCommInfo(@RequestParam("current")Long current,
                                   @RequestParam("size")Long size,
                                   @RequestParam(value = "prodName",required = false)String prodName,
                                   @RequestParam(value = "status",required = false)Integer status) {
        Page<ProdComm> page=new Page<>(current,size);
        page = prodCommService.page(page, new LambdaQueryWrapper<ProdComm>()
                .eq(ObjectUtils.isNotNull(status), ProdComm::getStatus, status)
                .like(StringUtils.hasText(prodName), ProdComm::getProdName, prodName)
                .orderByDesc(ProdComm::getCreateTime));
        return Result.success(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('prod:prodComm:info')")
    @Operation(description = "根据标识查询商品评论详情")
    public Result loadProdCommInfo(@PathVariable Long id) {
        ProdComm byId = prodCommService.getById(id);
        return Result.success(byId);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('prod:prodComm:update')")
    @Operation(description = "回复和审核商品评论")
    public Result replyAndExamineProdComm(@RequestBody ProdComm prodComm) {
        boolean flag=prodCommService.replyAndExamineProdComm(prodComm);
        return Result.handle(flag);
    }
}
