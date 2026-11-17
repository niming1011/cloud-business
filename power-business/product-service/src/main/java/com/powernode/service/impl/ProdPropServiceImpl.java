package com.powernode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.ProdProp;
import com.powernode.domain.ProdPropValue;
import com.powernode.mapper.ProdPropValueMapper;
import com.powernode.service.ProdPropService;
import com.powernode.mapper.ProdPropMapper;
import com.powernode.service.ProdPropValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【prod_prop】的数据库操作Service实现
* @createDate 2026-11-16 14:02:23
*/
@Service
public class ProdPropServiceImpl extends ServiceImpl<ProdPropMapper, ProdProp>
    implements ProdPropService{
    @Autowired
    private ProdPropMapper prodPropMapper;
    @Autowired
    private ProdPropValueMapper prodPropValueMapper;

    @Override
    public Page<ProdProp> queryProdSpecPage(Long current, Long size, String propName) {
        Page<ProdProp> page=new Page<>(current,size);
        page = prodPropMapper.selectPage(page, new LambdaQueryWrapper<ProdProp>()
                .like(StringUtils.hasText(propName), ProdProp::getPropName, propName));
        List<ProdProp> records = page.getRecords();
        if(CollectionUtils.isEmpty(records)){
            return page;
        }
        List<Long> propIdList = records.stream().map(ProdProp::getPropId).collect(Collectors.toList());
        List<ProdPropValue> prodPropValues = prodPropValueMapper.selectList(new LambdaQueryWrapper<ProdPropValue>()
                .in(ProdPropValue::getPropId, propIdList));
        records.forEach(prodProp -> {
            List<ProdPropValue> list = prodPropValues.stream().filter(prodPropValue -> prodPropValue.getPropId().equals(prodProp.getPropId())).toList();
            prodProp.setProdPropValues(list);
        });
        return page;
    }
}




