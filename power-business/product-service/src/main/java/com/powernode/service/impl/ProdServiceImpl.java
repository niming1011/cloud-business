package com.powernode.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.Prod;
import com.powernode.domain.ProdTag;
import com.powernode.domain.ProdTagReference;
import com.powernode.domain.Sku;
import com.powernode.mapper.ProdTagReferenceMapper;
import com.powernode.mapper.SkuMapper;
import com.powernode.service.ProdService;
import com.powernode.mapper.ProdMapper;
import com.powernode.service.ProdTagReferenceService;
import com.powernode.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【prod(商品)】的数据库操作Service实现
* @createDate 2026-11-16 14:02:23
*/
@Service
public class ProdServiceImpl extends ServiceImpl<ProdMapper, Prod>
    implements ProdService{
    @Autowired
    private ProdMapper prodMapper;
    @Autowired
    private ProdTagReferenceService prodTagReferenceService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private ProdTagReferenceMapper prodTagReferenceMapper;
    @Autowired
    private SkuMapper skuMapper;

    @Override
    public boolean saveProd(Prod prod) {
        prod.setShopId(1L);
        prod.setCreateTime(new Date());
        prod.setUpdateTime(new Date());
        prod.setSoldNum(0);
        prod.setPutawayTime(new Date());
        prod.setVersion(0);
       prod.setDeliveryMode(JSONUtil.toJsonStr(prod.getDeliveryModeVo()));
//        prod.setDeliveryMode(prod.getDeliverModeVO());
        int insert = prodMapper.insert(prod);
        if(insert>=1){
            Long prodId = prod.getProdId();
            List<Long> tagList = prod.getTagList();
            if(CollectionUtils.isNotEmpty(tagList)){
                List<ProdTagReference> prodTagReferenceList=new ArrayList<>();
                tagList.forEach(tag->{
                    ProdTagReference prodTagReference = new ProdTagReference();
                    prodTagReference.setProdId(prodId);
                    prodTagReference.setCreateTime(new Date());
                    prodTagReference.setShopId(1L);
                    prodTagReference.setStatus(1);
                    prodTagReference.setTagId(tag);
                    prodTagReference.setStatus(1);
                    prodTagReferenceList.add(prodTagReference);
                });
                 prodTagReferenceService.saveBatch(prodTagReferenceList);
            }
            List<Sku> skuList = prod.getSkuList();
            if(CollectionUtils.isNotEmpty(skuList)){
                skuList.forEach(sku -> {
                    sku.setProdId(prodId);
                    sku.setCreateTime(new Date());
                    sku.setUpdateTime(new Date());
                    sku.setVersion(0);
                    sku.setActualStocks(sku.getStocks());
                });
                 skuService.saveBatch(skuList);
            }
        return true;
        }
        return false;
    }

    @Override
    public Prod queryProdInfo(Long prodId) {
        Prod prod = prodMapper.selectById(prodId);
        List<ProdTagReference> prodTagReferenceList = prodTagReferenceMapper.selectList(new LambdaQueryWrapper<ProdTagReference>()
                .eq(ProdTagReference::getProdId, prod));
        if(CollectionUtils.isNotEmpty(prodTagReferenceList)){
            List<Long> list = prodTagReferenceList.stream().map(ProdTagReference::getTagId).toList();
            prod.setTagList(list);
        }
        List<Sku> skuList = skuMapper.selectList(new LambdaQueryWrapper<Sku>()
                .eq(Sku::getProdId, prodId));
        prod.setSkuList(skuList);

        return prod;
    }

    @Override
    public boolean modifyProdInfo(Prod prod) {
        Long prodId = prod.getProdId();
        prodTagReferenceMapper.delete(new LambdaQueryWrapper<ProdTagReference>()
                .eq(ProdTagReference::getProdId,prodId));
        List<Long> tagList = prod.getTagList();
        if(CollectionUtils.isNotEmpty(tagList)){
            List<ProdTagReference> prodTagReferenceList=new ArrayList<>();
            tagList.forEach(tag->{
                ProdTagReference prodTagReference = new ProdTagReference();
                prodTagReference.setProdId(prodId);
                prodTagReference.setCreateTime(new Date());
                prodTagReference.setShopId(1L);
                prodTagReference.setStatus(1);
                prodTagReference.setTagId(tag);
                prodTagReference.setStatus(1);
                prodTagReferenceList.add(prodTagReference);
            });
            prodTagReferenceService.saveBatch(prodTagReferenceList);
        }
        List<Sku> skuList = prod.getSkuList();
        skuList.forEach(sku -> {
            sku.setUpdateTime(new Date());
            sku.setActualStocks(sku.getStocks());
        });
        skuService.updateBatchById(skuList);
        prod.setUpdateTime(new Date());
        int i = prodMapper.updateById(prod);
        return i>=1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeProd(List<Long> prodIds) {
        prodTagReferenceMapper.delete(new LambdaQueryWrapper<ProdTagReference>()
                .in(ProdTagReference::getProdId,prodIds));
        skuMapper.delete(new LambdaQueryWrapper<Sku>()
                .in(Sku::getProdId,prodIds));
        return this.removeBatchByIds(prodIds);
    }
}




