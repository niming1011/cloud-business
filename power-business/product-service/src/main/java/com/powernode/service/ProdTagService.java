package com.powernode.service;

import com.powernode.domain.ProdTag;
import com.baomidou.mybatisplus.spring.service.IService;

/**
* @author Administrator
* @description 针对表【prod_tag(商品分组表)】的数据库操作Service
* @createDate 2026-11-16 14:02:23
*/
public interface ProdTagService extends IService<ProdTag> {

    boolean saveProdTag(ProdTag prodTag);

    boolean modifyProdTag(ProdTag prodTag);
}
