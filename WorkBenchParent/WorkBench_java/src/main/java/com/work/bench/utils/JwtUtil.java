package com.work.bench.utils;

import com.work.bench.enums.TokenType;
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
    // accessToken 有效期
//    private static final long ACCESS_EXPIRE_TIME = 10 * 60 * 1000L;       // 10分钟
    private static final long ACCESS_EXPIRE_TIME = 30 * 1000L; // 30秒 = 30000毫秒
    // refreshToken 有效期
    private static final long REFRESH_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L; // 7天

    public static long getRefreshExpireTime() {
        return REFRESH_EXPIRE_TIME;
    }

    public static long getAccessExpireTime() {
        return ACCESS_EXPIRE_TIME;
    }

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * 生成 token
     *
     * @param userId    用户id作为信息
     * @param tokenType token类型
     * @return
     */
    public String createToken(Integer userId, TokenType tokenType) {
        // 过期时间
        Date now = new Date();
        // 根据类型选择过期时间
        long expireTime = tokenType == TokenType.ACCESS
                ? ACCESS_EXPIRE_TIME
                : REFRESH_EXPIRE_TIME;
        Date expire = new Date(now.getTime() + expireTime);
        return Jwts.builder()
                // 用户id作为用户唯一表示
                .setSubject(String.valueOf(userId))
                //token 类型
                .claim("type",tokenType.name().toLowerCase())
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
