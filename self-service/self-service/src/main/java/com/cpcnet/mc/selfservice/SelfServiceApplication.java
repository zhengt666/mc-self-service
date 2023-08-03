package com.cpcnet.mc.selfservice;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.cpcnet")
@EnableDiscoveryClient
@EnableKnife4j
@EnableFeignClients(basePackages = "com.cpcnet")
public class SelfServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfServiceApplication.class, args);
    }

}
