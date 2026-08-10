package com.work.bench.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 一个关于Security的工具类
 *
 * @author 洁心未眠
 * @Package com.work.bench.security
 * @date 2026/8/10 19:17
 */
@Component
public class SecurityUtils {

    /**
     * 由于我现在的 JwtAuthenticationFilter 在校验token时，只是存放了userId在security中，后期获取时可以比较麻烦
     * 所以这里定义一个通用方法来进行获取
     * @return
     */
    public static Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Integer) authentication.getPrincipal();
    }
}
