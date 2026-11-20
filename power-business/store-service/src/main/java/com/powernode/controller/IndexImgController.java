package com.powernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.IndexImg;
import com.powernode.result.Result;
import com.powernode.service.IndexImgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.index.IndexDefinition;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin/indexImg")
@Tag(name = "轮播图接口管理")
@RestController
public class IndexImgController {
    @Autowired
    private IndexImgService indexImgService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('admin:indexImg:page')")
    @Operation(description = "多条件分页查询轮播图")
    public Result loadIndexImgInfo(@RequestParam("current")Long current,
                                   @RequestParam("size")Long size,
                                   @RequestParam(value = "status",required = false)Integer status) {
        Page<IndexImg> page = new Page<>(current, size);
        page = indexImgService.page(page, new LambdaQueryWrapper<IndexImg>()
                .eq(ObjectUtils.isNotNull(status), IndexImg::getStatus, status)
                .orderByDesc(IndexImg::getSeq));
        return Result.success(page);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:indexImg:save')")
    @Operation(description = "新增轮播图")
    public Result saveIndexImg(@RequestBody IndexImg indexImg) {
        boolean save=indexImgService.saveIndexImg(indexImg);
        return Result.handle(save);
    }

    @GetMapping("info/{imgId}")
    @PreAuthorize("hasAuthority('admin:indexImg:info')")
    @Operation(description = "根据标识查询轮播图信息")
    public Result loadIndexImgInfo(@PathVariable Long imgId) {
        IndexImg indexImg=indexImgService.queryIndexImgInfo(imgId);
        return Result.success(indexImg);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:indexImg:update')")
    @Operation(description = "修改轮播图内容")
    public Result modifyIndexImgInfo(@RequestBody IndexImg indexImg) {
        boolean modified = indexImgService.modifyIndexImg(indexImg);
        return Result.handle(modified);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:indexImg:delete')")
    @Operation(description = "批量删除轮播图")
    public Result removeIndexImg(@RequestBody List<Long> imgIds) {
        boolean removed = indexImgService.removeBatchByIds(imgIds);
        return Result.handle(removed);
    }
}
