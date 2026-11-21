package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.OrderItem;
import com.powernode.service.OrderItemService;
import com.powernode.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【order_item(订单项)】的数据库操作Service实现
* @createDate 2026-11-20 17:14:35
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




