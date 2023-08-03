package com.cpcnet.mc.mcgateway.filter;

import com.cpcnet.mc.mcgateway.config.FilterIgnorePropertiesConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @description: 身份认证
 * @author: Ebon Zheng
 * @create: 2023-08-01 15:50
 **/
@Slf4j
@Component
@AllArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {

    private final FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    private final RedisTemplate<String, String> stringRedisTemplate;

    private static final String AUTHORIZATION = "Authorization";

    private static final String AUTH_TOKEN_INFO = "auth:access_token:";

    private static PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
