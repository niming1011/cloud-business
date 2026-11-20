package com.powernode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.powernode.domain.Order;
import com.powernode.service.OrderService;
import com.powernode.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【order(订单表)】的数据库操作Service实现
* @createDate 2026-11-20 17:14:35
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




