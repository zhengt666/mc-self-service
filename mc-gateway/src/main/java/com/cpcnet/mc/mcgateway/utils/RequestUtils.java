package com.cpcnet.mc.mcgateway.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetAddress;
import java.net.InetSocketAddress;


/**
 * 请求相关工具类
 *
 * @author 机考（企业版）项目组
 * @date 2020/5/13
 */
public class RequestUtils {
    private RequestUtils() {
    }

    private static final String UNKNOWN = "unknown";

    /**
     * 取客户端真实的IP地址
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getIpAddr(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = headers.getFirst("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (!StringUtils.isBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            InetSocketAddress remoteAddress = request.getRemoteAddress();
            InetAddress address = remoteAddress.getAddress();
            return address.getHostAddress();
        }
    }
}
