package com.powernode.controller;

import com.powernode.domain.MemberAddr;
import com.powernode.result.Result;
import com.powernode.service.MemberAddrService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("p/address")
@Tag(name="用户地址接口管理")
@RestController
public class MemberAddrController {
    @Autowired
    private MemberAddrService memberAddrService;
    @GetMapping("getMemberAddrById")
    Result getMemberAddrById(@RequestParam  Long addrOrderId){
        MemberAddr byId = memberAddrService.getById(addrOrderId);
        return Result.success(byId);
    }
}
