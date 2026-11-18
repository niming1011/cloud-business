package com.powernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.ProdProp;
import com.powernode.domain.ProdPropValue;
import com.powernode.result.Result;
import com.powernode.service.ProdPropService;
import com.powernode.service.ProdPropValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("prod/spec")
@Tag(name="商品规格接口管理")
@RestController
public class ProdSpecController {
    @Autowired
    private ProdPropService prodPropService;
    @Autowired
    private ProdPropValueService prodPropValueService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('prod:spec:page')")
    @Operation(description = "多条件分页查询商品规格")
    public Result loadProdSpecPage(@RequestParam("current") Long current,
                                   @RequestParam("size")Long size,
                                   @RequestParam(value = "propName",required = false)String propName) {
       Page<ProdProp> page=prodPropService.queryProdSpecPage(current,size,propName);
        return Result.success(page);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('prod:spec:save')")
    @Operation(description = "新增商品规格")
    public Result saveProdSpec(@RequestBody ProdProp prodProp) {
        boolean save=prodPropService.saveProdPropSpec(prodProp);
        return Result.handle(save);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('prod:spec:update')")
    @Operation(description = "修改商品规格")
    public Result modifyProdSpec(@RequestBody ProdProp prodProp) {
        boolean modified=prodPropService.modifyProdSpec(prodProp);
        return Result.handle(modified);
    }

    @DeleteMapping("{propId}")
    @PreAuthorize("hasAuthority('prod:spec:delete')")
    @Operation(description = "删除商品规格信息")
    public Result removeProdSpec(@PathVariable Long propId) {
        boolean remove=prodPropService.removeProdSpec(propId);
        return Result.handle(remove);
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('prod:spec:page')")
    @Operation(description = "查询商品属性集合")
    public Result loadProdSpecList() {
        List<ProdProp> prodPropList=prodPropService.queryProdSpecList();
        return Result.success(prodPropList);
    }

    @GetMapping("listSpecValue/{propId}")
    @PreAuthorize("hasAuthority('prod:spec:page')")
    @Operation(description = "根据商品属性集合查询商品属性值集合")
    public Result loadSpecValueBySpecId(@PathVariable Long propId) {
        List<ProdPropValue> prodPropValueList=prodPropValueService.list(new LambdaQueryWrapper<ProdPropValue>()
                .eq(ProdPropValue::getPropId,propId));
        return Result.success(prodPropValueList);
    }
}
