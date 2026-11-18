package com.powernode.service;

import com.powernode.domain.Prod;
import com.baomidou.mybatisplus.spring.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【prod(商品)】的数据库操作Service
* @createDate 2026-11-16 14:02:23
*/
public interface ProdService extends IService<Prod> {

    boolean saveProd(Prod prod);

    Prod queryProdInfo(Long prodId);

    boolean modifyProdInfo(Prod prod);

    boolean removeProd(List<Long> prodId);
}
