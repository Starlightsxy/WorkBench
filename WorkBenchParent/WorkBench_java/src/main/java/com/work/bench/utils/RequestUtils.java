package com.work.bench.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Request 的工具类
 * @author 洁心未眠
 * @Package com.work.bench.utils
 * @date 2026/8/17 16:35
 */
public final class RequestUtils {
    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // X-Forwarded-For 可能是：
        // 192.168.1.10, 192.168.1.20, ...
        if (ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }
}
