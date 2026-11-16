package com.powernode.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.SysLog;
import com.powernode.result.Result;
import com.powernode.service.SysLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("sys/log")
@RestController
@Tag(name = "系统日志接口")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('sys:log:page')")
    @Operation(description = "多条件分页查询系统操作日志")
    public Result loadLogPage(@RequestParam("current") Long current,
                              @RequestParam("size") Long size,
                              @RequestParam(value = "userId",required = false)Long userId,
                              @RequestParam(value = "operation",required = false)String operation) {
        Page page=new Page<SysLog>(current,size);
        page = sysLogService.page(page, new LambdaQueryWrapper<SysLog>()
                .eq(ObjectUtil.isNotEmpty(userId),SysLog::getUserId, userId)
                .like(StringUtils.hasText(operation),SysLog::getOperation, operation));

        return Result.success(page);
    }
}
