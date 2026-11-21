package com.powernode.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.domain.Order;
import com.baomidou.mybatisplus.spring.service.IService;

import java.util.Date;

/**
* @author Administrator
* @description 针对表【order(订单表)】的数据库操作Service
* @createDate 2026-11-20 17:14:35
*/
public interface OrderService extends IService<Order> {

    Page<Order> queryOrderPage(Page<Order> page, String orderNumber, Integer status, Date startTime, Date endTime);

    Order queryOrderInfo(String orderNumber);
}
