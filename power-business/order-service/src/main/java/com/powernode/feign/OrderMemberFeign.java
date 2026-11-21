package com.powernode.feign;

import com.powernode.feign.sentinal.OrderMemberFeignSentinal;
import com.powernode.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-service",fallback = OrderMemberFeignSentinal.class )
public interface OrderMemberFeign {
    @GetMapping("/p/address/getMemberAddrById")
    Result getMemberAddrById(@RequestParam  Long addrOrderId);
    @GetMapping("admin/user/getNickNameByOpenId")
    Result getNickNameByOpenId(@RequestParam String openId);

}
