package com.powernode.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.ProdProp;
import com.powernode.result.Result;
import com.powernode.service.ProdPropService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("prod/spec")
@Tag(name="商品规格接口管理")
@RestController
public class ProdSpecController {
    @Autowired
    private ProdPropService prodPropService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('prod:spec:page')")
    @Operation(description = "多条件分页查询商品规格")
    public Result loadProdSpecPage(@RequestParam("current") Long current,
                                   @RequestParam("size")Long size,
                                   @RequestParam(value = "propName",required = false)String propName) {
       Page<ProdProp> page=prodPropService.queryProdSpecPage(current,size,propName);
        return Result.success(page);
    }
}
