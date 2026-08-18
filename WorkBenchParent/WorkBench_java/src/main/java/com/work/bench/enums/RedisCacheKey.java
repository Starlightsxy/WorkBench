package com.work.bench.enums;

import lombok.Getter;

/**
 * 用于存储 Redis 缓存的 key
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/11 09:51
 */
@Getter
public enum RedisCacheKey {
    // 缓存庸碌登陆后的信息key
    REDIS_CACHE_USER_KEY("login:user:"),
    // refreshToken Key
    REFRESH_TOKEN("login:refresh:");

    private final String value;

    RedisCacheKey(String value) {
        this.value = value;
    }
}
