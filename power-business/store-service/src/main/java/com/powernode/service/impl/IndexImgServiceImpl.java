package com.powernode.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.powernode.constant.BusinessEnum;
import com.powernode.domain.IndexImg;
import com.powernode.domain.Prod;
import com.powernode.exception.BusinessException;
import com.powernode.feign.StoreProdFeign;
import com.powernode.result.Result;
import com.powernode.service.IndexImgService;
import com.powernode.mapper.IndexImgMapper;
import net.sf.jsqlparser.statement.create.table.Index;
import org.aspectj.util.PartialOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【index_img(主页轮播图)】的数据库操作Service实现
* @createDate 2026-11-18 16:59:56
*/
@Service
public class IndexImgServiceImpl extends ServiceImpl<IndexImgMapper, IndexImg>
    implements IndexImgService{
    @Autowired
    private IndexImgMapper indexImgMapper;
    @Autowired
    private StoreProdFeign storeProdFeign;

    @Override
    public boolean saveIndexImg(IndexImg indexImg) {
        indexImg.setShopId(1L);
        indexImg.setCreateTime(new Date());
        Integer type = indexImg.getType();
        if(-1==type){
            indexImg.setProdId(null);
        }
        int insert = indexImgMapper.insert(indexImg);
        return insert>=1;
    }

    @Override
    public IndexImg queryIndexImgInfo(Long imgId) {
        IndexImg indexImg = indexImgMapper.selectById(imgId);
        Integer type = indexImg.getType();
        if(0==type){
            Long prodId = indexImg.getProdId();
            //根据商品id查询商品信息
            Result result = storeProdFeign.getProdListByIds(CollectionUtils.toList(prodId));
            if(BusinessEnum.OPERATION_FAIL.getCode().equals(result.getCode())){
                throw new BusinessException(result.getMsg());
            }

            List<Prod> prods = Convert.toList(Prod.class, result.getData());

            if(CollectionUtils.isNotEmpty(prods)){
                Prod prod = prods.get(0);
                indexImg.setProdName(prod.getProdName());
                indexImg.setPic(prod.getPic());
            }
        }
        return indexImg;
    }

    @Override
    public boolean modifyIndexImg(IndexImg indexImg) {
        int i = indexImgMapper.updateById(indexImg);
        if(i>=1){
            if(indexImg.getType()==-1){
                boolean update = new LambdaUpdateChainWrapper<IndexImg>(indexImgMapper)
                        .eq(IndexImg::getImgId, indexImg.getImgId())
                        .set(IndexImg::getProdId, null)
                        .update();
                return update;
            }
            return true;
        }
        return false;
    }
}




