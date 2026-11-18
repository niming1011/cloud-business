package com.powernode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.constant.StoreConstant;
import com.powernode.domain.Area;
import com.powernode.service.AreaService;
import com.powernode.mapper.AreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【area】的数据库操作Service实现
* @createDate 2026-11-18 16:59:56
*/
@Service
@CacheConfig(cacheNames = "com.powernode.service.impl.AreaServiceImpl")
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area>
    implements AreaService{
    @Autowired
    private AreaMapper areaMapper;

    @Override
    @Cacheable(StoreConstant.ALL_AREA_KEY)
    public List<Area> queryAllAreaInfo() {
        List<Area> areaList = areaMapper.selectList(new LambdaQueryWrapper<>());
        return areaList;
    }
}




