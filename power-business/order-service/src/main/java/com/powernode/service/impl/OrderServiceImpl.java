package com.powernode.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.constant.BusinessEnum;
import com.powernode.domain.Member;
import com.powernode.domain.MemberAddr;
import com.powernode.domain.Order;
import com.powernode.domain.OrderItem;
import com.powernode.exception.BusinessException;
import com.powernode.feign.OrderMemberFeign;
import com.powernode.mapper.OrderItemMapper;
import com.powernode.result.Result;
import com.powernode.service.OrderService;
import com.powernode.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【order(订单表)】的数据库操作Service实现
* @createDate 2026-11-20 17:14:35
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderMemberFeign orderMemberFeign;

    @Override
    public Page<Order> queryOrderPage(Page<Order> page, String orderNumber, Integer status, Date startTime, Date endTime) {
        Page<Order> orderPage = orderMapper.selectPage(page, new LambdaQueryWrapper<Order>()
                .eq(ObjectUtils.isNotNull(status), Order::getStatus, status)
                .between(ObjectUtil.isAllNotEmpty(startTime, endTime), Order::getCreateTime, startTime, endTime)
                .like(StringUtils.hasText(orderNumber), Order::getOrderNumber, orderNumber)
                .orderByDesc(Order::getCreateTime));
        List<Order> records = orderPage.getRecords();
        if(CollectionUtils.isEmpty(records)){
            return orderPage;
        }
        List<String> orderNumberList = records.stream().map(Order::getOrderNumber).toList();
        List<OrderItem> orderItems = orderItemMapper.selectList(new LambdaQueryWrapper<OrderItem>()
                .in(OrderItem::getOrderNumber, orderNumberList));
        records.forEach(order -> {
            List<OrderItem> orderItemList = orderItems.stream().filter(orderItem -> orderItem.getOrderNumber().equals(order.getOrderNumber())).toList();
            order.setOrderItems(orderItemList);
        });
        return orderPage;
    }

    @Override
    public Order queryOrderInfo(String orderNumber) {
        Order order = orderMapper.selectOne(new LambdaQueryWrapper<Order>()
                .eq(Order::getOrderNumber, orderNumber));
        List<OrderItem> orderItems = orderItemMapper.selectList(new LambdaQueryWrapper<OrderItem>()
                .eq(OrderItem::getOrderNumber, orderNumber));
        order.setOrderItems(orderItems);
        Long addrOrderId = order.getAddrOrderId();
        //通过用户地址标识查询地址详情
        Result result = orderMemberFeign.getMemberAddrById(addrOrderId);
        if(result.getCode().equals(BusinessEnum.OPERATION_FAIL.getCode())){
            throw new BusinessException("远程调用失败，order服务调用member服务获取用户地址信息");
        }
        MemberAddr memberAddr = Convert.convert(MemberAddr.class, result.getData());
        order.setUserAddrOrder(memberAddr);
        Result result2 = orderMemberFeign.getNickNameByOpenId(order.getOpenId());
        if(result2.getCode().equals(BusinessEnum.OPERATION_FAIL.getCode())){
            throw new BusinessException("远程调用失败，order服务调用member服务获取用户昵称信息");
        }
        String nickName = Convert.convert(String.class, result2.getData());
        order.setNickName(nickName);
        return order;
    }
}




