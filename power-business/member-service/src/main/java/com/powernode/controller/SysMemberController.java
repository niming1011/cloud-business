package com.powernode.controller;

import cn.hutool.core.collection.RandomAccessAvgPartition;
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

import java.util.ArrayList;
import java.util.List;

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

    @PutMapping
    @PreAuthorize("hasAuthority('admin:user:update')")
    @Operation(description = "修改会员信息")
    public Result modifySysMemberInfo(@RequestBody Member member) {
        boolean update = memberService.updateById(member);
        return Result.handle(update);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:user:delete')")
    @Operation(description = "批量删除会员")
    public Result removeSysMember(@RequestBody List<Integer> ids) {
        List<Member> members=new ArrayList<>();
        ids.forEach(id->{
            Member member = new Member();
            member.setId(id);
            member.setStatus(-1);
            members.add(member);
        });
        boolean update = memberService.updateBatchById(members);
        return Result.handle(update);
    }
}
