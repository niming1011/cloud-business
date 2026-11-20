package com.powernode.feign;

import com.powernode.domain.Prod;
import com.powernode.feign.sentinel.StoreProdFeignSentinel;
import com.powernode.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "product-service",fallback= StoreProdFeignSentinel.class)
public interface StoreProdFeign {
    @GetMapping("prod/prod/getProdInfoById")
    Result getProdListByIds(@RequestParam("prodIdList") List<Long> prodIdList);
}
