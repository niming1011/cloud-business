package com.powernode.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.powernode.config.WhiteUrlsConfig;
import com.powernode.constant.BusinessEnum;
import com.powernode.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.powernode.constant.AuthConstants;
@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {
    @Autowired
    private WhiteUrlsConfig whiteUrlsConfig;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        if(whiteUrlsConfig.getAllowUrls().contains(path)){
            return chain.filter(exchange);
        }else{
            String authorization = request.getHeaders().getFirst(AuthConstants.AUTHORIZATION);
            String token = authorization.replaceFirst(AuthConstants.BEARER, "");

            if(StringUtils.hasText(token)&&redisTemplate.hasKey(AuthConstants.LOGIN_TOKEN_PREFIX+token)){
                return chain.filter(exchange);
            }
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().set("Content-Type","application/json;charset=utf-8");
            Result fail = Result.fail(BusinessEnum.UN_AUTHORIZATION);
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] serialize = null;
            try {
                serialize = objectMapper.writeValueAsBytes(fail);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            DataBuffer dataBuffer=response.bufferFactory().wrap(serialize);
            return response.writeWith(Mono.just(dataBuffer));
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
