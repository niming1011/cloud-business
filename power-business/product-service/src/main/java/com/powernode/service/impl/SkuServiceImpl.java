package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.Sku;
import com.powernode.service.SkuService;
import com.powernode.mapper.SkuMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【sku(单品SKU表)】的数据库操作Service实现
* @createDate 2026-11-16 14:02:23
*/
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku>
    implements SkuService{

}




