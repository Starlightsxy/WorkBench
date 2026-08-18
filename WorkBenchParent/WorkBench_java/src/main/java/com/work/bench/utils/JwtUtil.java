package com.work.bench.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JWT 工具类 负责 JWT 的生成、解析、校验
 *
 * @author 洁心未眠
 * @Package com.work.bench.security
 * @date 2026/8/10 16:36
 */
@Component
public final class JwtUtil {
    // 密钥
    private static final String SECRET_KEY = "workbench-jwt-secret-key-workbench";
    // token有效期
    private static final Long EXPIRE_TIME = 10 * 60 * 1000L;
    public static   Long getExpireTime() {
        return EXPIRE_TIME;
    }
//    public static final Long EXPIRE_TIME = 30 * 1000L; // 或直接写 30000L

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * 生成 token
     *
     * @param userId 根据 userId 生成token
     * @return
     */
    public String createToken(Integer userId) {
        // 过期时间
        Date now = new Date();
        Date expire = new Date(now.getTime() + EXPIRE_TIME);
        return Jwts.builder()
                // 用户id作为用户唯一表示
                .setSubject(String.valueOf(userId))
                // 签发时间
                .setIssuedAt(now)
                // 过期时间
                .setExpiration(expire)
                // .addClaims()// 配置自定义字段 适用于RBAC
                // 签名算法 HS256算法 的 SecretKey 至少 256 bit 换算一下也就是32byte，
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 校验 token
     *
     * @param token
     * @return
     */
    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }


}
