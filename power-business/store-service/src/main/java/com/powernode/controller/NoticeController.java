package com.powernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.Notice;
import com.powernode.result.Result;
import com.powernode.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("shop/notice")
@Tag(name="公告业务管理模块")
@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('shop:notice:page')")
    @Operation(description = "多条件分页查询公告列表")
    public Result loadNoticePage(@RequestParam("current") Long current,
                                 @RequestParam("size") Long size,
                                 @RequestParam(value = "title",required = false)String title,
                                 @RequestParam(value = "status",required = false)Integer status,
                                 @RequestParam(value = "isTop",required = false)Integer isTop) {
        Page<Notice> page = new Page<>(current, size);
        page = noticeService.page(page, new LambdaQueryWrapper<Notice>()
                .eq(ObjectUtils.isNotNull(status), Notice::getStatus, status)
                .eq(ObjectUtils.isNotNull(isTop), Notice::getIsTop, isTop)
                .like(StringUtils.hasText(title), Notice::getTitle, title)
                .orderByDesc(Notice::getCreateTime));
        return Result.success(page);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('shop:notice:page')")
    @Operation(description = "新增公告")
    public Result saveNotice(@RequestBody Notice notice) {
        boolean save=noticeService.saveNotice(notice);
        return Result.handle(save);
    }

    @GetMapping("info/{noticeId}")
    @PreAuthorize("hasAuthority('shop:notice:info')")
    @Operation(description = "根据标识查询公告对象")
    public Result loadNoticeInfo(@PathVariable Long noticeId) {
        Notice byId = noticeService.getById(noticeId);
        return Result.success(byId);
    }
}
