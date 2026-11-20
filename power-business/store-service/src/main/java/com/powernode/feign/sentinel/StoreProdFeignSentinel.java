package com.powernode.feign.sentinel;

import com.powernode.feign.StoreProdFeign;
import com.powernode.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StoreProdFeignSentinel implements StoreProdFeign {

    @Override
    public Result getProdListByIds(List<Long> prodIdList) {
        log.error("根据商品id集合查询商品详细信息");
        return null;
    }
}
