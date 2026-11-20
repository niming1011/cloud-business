package com.powernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.Member;
import com.powernode.result.Result;
import com.powernode.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin/user")
@Tag(name="后台管理系统会员接口管理")
@RestController
public class SysMemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('admin:user:page')")
    @Operation(description = "多条件分页查询会员")
    public Result loadSysMember(@RequestParam Long current,
                                @RequestParam Long size,
                                @RequestParam(required = false) String nickName,
                                @RequestParam(required = false) Integer status) {
        Page<Member> page = new Page<>(current,size);
        page = memberService.page(page, new LambdaQueryWrapper<Member>()
                .eq(ObjectUtils.isNotNull(status), Member::getStatus, status)
                .like(StringUtils.hasText(nickName), Member::getNickName, nickName));
        return Result.success(page);
    }

    @GetMapping("info/{id}")
    @PreAuthorize("hasAuthority('admin:user:info')")
    @Operation(description = "根据标识查询会员信息")
    public Result querySysMemberInfo(@PathVariable Long id) {
        Member byId = memberService.getById(id);
        return Result.success(byId);
    }
}
