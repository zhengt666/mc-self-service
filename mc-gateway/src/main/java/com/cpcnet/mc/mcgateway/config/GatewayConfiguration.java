package com.cpcnet.mc.mcgateway.config;

import com.cpcnet.mc.mcgateway.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 网关通用配置
 * @author: Ebon Zheng
 * @create: 2023-08-01 15:18
 **/
@Configuration
public class GatewayConfiguration {

    /**
     * 异常处理类
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
