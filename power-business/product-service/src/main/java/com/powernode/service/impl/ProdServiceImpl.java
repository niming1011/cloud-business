package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.Prod;
import com.powernode.service.ProdService;
import com.powernode.mapper.ProdMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【prod(商品)】的数据库操作Service实现
* @createDate 2026-11-16 14:02:23
*/
@Service
public class ProdServiceImpl extends ServiceImpl<ProdMapper, Prod>
    implements ProdService{

}




