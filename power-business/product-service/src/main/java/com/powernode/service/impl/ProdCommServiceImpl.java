package com.powernode.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.powernode.domain.ProdComm;
import com.powernode.service.ProdCommService;
import com.powernode.mapper.ProdCommMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
* @author Administrator
* @description 针对表【prod_comm(商品评论)】的数据库操作Service实现
* @createDate 2026-11-16 14:02:23
*/
@Service
public class ProdCommServiceImpl extends ServiceImpl<ProdCommMapper, ProdComm>
    implements ProdCommService{
    @Autowired
    private ProdCommMapper prodCommMapper;

    @Override
    public boolean replyAndExamineProdComm(ProdComm prodComm) {
        String replyContent = prodComm.getReplyContent();
        if(StringUtils.hasText(replyContent)){
            prodComm.setCreateTime(new Date());
            prodComm.setReplySts(1);
        }
        return prodCommMapper.updateById(prodComm)>=1;
    }
}




