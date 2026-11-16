package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.ProdTag;
import com.powernode.service.ProdTagService;
import com.powernode.mapper.ProdTagMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【prod_tag(商品分组表)】的数据库操作Service实现
* @createDate 2026-11-16 14:02:23
*/
@Service
public class ProdTagServiceImpl extends ServiceImpl<ProdTagMapper, ProdTag>
    implements ProdTagService{

}




