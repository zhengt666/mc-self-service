package com.cpcnet.mc.mcgateway.filter;

import com.cpcnet.mc.mcgateway.utils.RequestUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

/**
 * @description: 负载配置
 * @author: Ebon Zheng
 * @create: 2023-08-01 15:56
 **/
public class LoadBalancerFilter extends LoadBalancerClientFilter {
    public LoadBalancerFilter(LoadBalancerClient loadBalancer, LoadBalancerProperties properties) {
        super(loadBalancer, properties);
    }

    @Override
    protected ServiceInstance choose(ServerWebExchange exchange) {
        String ipAddr = RequestUtils.getIpAddr(exchange.getRequest());
        if (this.loadBalancer instanceof RibbonLoadBalancerClient) {
            RibbonLoadBalancerClient client = (RibbonLoadBalancerClient) this.loadBalancer;
            String serviceId = ((URI) exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR)).getHost();
            return client.choose(serviceId, ipAddr);
        }
        return super.choose(exchange);
    }
}
