package com.powernode.feign.sentinal;

import com.powernode.feign.OrderMemberFeign;
import com.powernode.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderMemberFeignSentinal implements OrderMemberFeign {
    @Override
    public Result getMemberAddrById(Long addrOrderId) {
        log.error("远程调用失败，order服务调用member服务获取用户地址信息");
        return null;
    }

    @Override
    public Result getNickNameByOpenId(String openId) {
        log.error("远程调用失败，order服务调用member服务获取用户昵称信息");
        return null;
    }
}
