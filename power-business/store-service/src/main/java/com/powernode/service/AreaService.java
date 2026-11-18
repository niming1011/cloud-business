package com.powernode.service;

import com.powernode.domain.Area;
import com.baomidou.mybatisplus.spring.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【area】的数据库操作Service
* @createDate 2026-11-18 16:59:56
*/
public interface AreaService extends IService<Area> {

    List<Area> queryAllAreaInfo();

}
