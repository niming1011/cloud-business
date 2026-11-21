package com.powernode.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.Order;
import com.powernode.result.Result;
import com.powernode.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.encrypt.EnvironmentDecryptApplicationInitializer;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("order/order")
@Tag(name="订单业务接口管理")
@RestController
public class SysOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("page")
    @PreAuthorize("hasAuthority('order:order:page')")
    @Operation(description = "多条件分页查询订单")
    public Result loadOrderPage(@RequestParam Long current,
                                @RequestParam Long size,
                                @RequestParam(required = false) String orderNumber,
                                @RequestParam(required = false) Integer status,
                                @RequestParam(required = false)Date startTime,
                                @RequestParam(required = false)Date endTime) {
        Page<Order> page = new Page<>(current,size);
        page = orderService.queryOrderPage(page,orderNumber,status,startTime,endTime);
        return Result.success(page);
    }

    @GetMapping("orderInfo/{orderNumber}")
    @PreAuthorize("hasAuthority('order:order:info')")
    @Operation(description = "根据订单编号查询订单详情")
    public Result loadOrderInfo(@PathVariable String orderNumber) {
        Order order=orderService.queryOrderInfo(orderNumber);
        return Result.success(order);
    }

    @GetMapping("soldExcel")
    @PreAuthorize("hasAuthority('order:order:soldExcel')")
    @Operation(description = "导出销售记录")
    public Result exportSoldOrderRecordExcel() {
        List<Order> list = orderService.list(new LambdaQueryWrapper<Order>()
                .orderByDesc(Order::getCreateTime));

        String fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, Order.class).sheet("模板").doWrite(list);

        return Result.success(null);
    }
}
