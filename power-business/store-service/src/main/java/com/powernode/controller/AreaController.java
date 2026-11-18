package com.powernode.controller;

import com.powernode.domain.Area;
import com.powernode.result.Result;
import com.powernode.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("admin/area")
@RestController
@Tag(name="地区业务接口管理")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('admin:area:list')")
    @Operation(description = "查询全国地区列表")
    public Result loadAllAreaInfo() {
        List<Area> areaList= areaService.queryAllAreaInfo();
        return Result.success(areaList);
    }
}
