package com.work.bench.security;

import com.work.bench.enums.RedisCacheKey;
import com.work.bench.utils.BaseContext;
import com.work.bench.utils.JwtUtil;
import com.work.bench.vo.user.UserInfoVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * jwt 认证过滤器
 *
 * @author 洁心未眠
 * @Package com.work.bench.security
 * @date 2026/8/10 16:36
 */
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, Object> jsonRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 获取请求头
            String header = request.getHeader("Authorization");
            // 没 token 直接放行
            if (header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 截取token
            String token = header.substring(7);

            // 校验 token
            Claims claims = jwtUtil.validateToken(token);
            if (claims != null) {

                String type = claims.get("type", String.class);
                // 如果是refreshToken,就不能拿来访问业务
                if (!"access".equals(type)) {
                    filterChain.doFilter(request, response);
                    return;
                }
                // 是accessToken就可以拿来访问业务
                Integer userId = Integer.parseInt(claims.getSubject());


                UserInfoVO userInfoVO = (UserInfoVO) jsonRedisTemplate.opsForValue()
                        .get(RedisCacheKey.REDIS_CACHE_USER_KEY.getValue() + userId);

                // 将用户id存储到当前线程， 必须保证redis中有信息才允许设置
                BaseContext.setCurrentId(userId);

                // 处理redis可能没有数据
                if (userInfoVO == null) {
                    filterChain.doFilter(request, response);
                    return;
                }

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userInfoVO, null, Collections.emptyList());


                // spring security 保存登录用户
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


            filterChain.doFilter(request, response);
        } finally {
            // 防止ThreadLocal数据污染
            BaseContext.removeCurrentId();

            // 清理当前线程的SecurityContext
            SecurityContextHolder.clearContext();
        }


    }
}
