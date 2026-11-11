package com.powernode.config;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.powernode.constant.AuthConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if(ObjectUtils.isNotNull(requestAttributes)){
            HttpServletRequest request = requestAttributes.getRequest();
            if(ObjectUtils.isNotEmpty(request)){
                String authorization = request.getHeader(AuthConstants.AUTHORIZATION);
                if(StringUtils.hasText(authorization)){
                    template.header(AuthConstants.AUTHORIZATION,authorization);
                    return ;
                }
            }
        }
        template.header(AuthConstants.AUTHORIZATION,AuthConstants.BEARER+"67740406-72c3-46be-a7c4-f92a628f8202");

    }
}
