package com.work.bench.config;

import com.work.bench.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * security 配置
 *
 * @author 洁心未眠
 * @Package com.work.bench.security
 * @date 2026/8/10 16:42
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    // jwt filter链
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 提供调用认证的入口
     * @param configuration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    /**
     * 告诉 Spring Security 以什么方式进行密码加密和校验，
     * @return 这里使用的是BCrypt模式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    /**
     * 用户认证具体怎么做
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(
                userDetailsService
        );

        provider.setPasswordEncoder(
                passwordEncoder()
        );


        return provider;

    }

    /**
     * 配置路径黑白名单，哪些需要经过登录才可以访问，哪些不需要登录就能访问
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()// 禁用csrf 因为使用jwt所以不需要这个
                // 使用 CorsConfig 跨域
                .cors(cors -> {})

                // 配置拦截
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login","/ws","/user/refresh").permitAll() // 允许访问login，
                        .anyRequest().authenticated()// 其他请求都必须走认证
                )

                // 不需要使用session存储登录状态
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 告诉 Spring Security：账号密码认证时，使用哪个 AuthenticationProvider。
                .authenticationProvider(authenticationProvider())

                .exceptionHandling(exception -> exception
                        // 没有认证身份 -> 401
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("{\"code\":401,\"message\":\"未登录或Token无效\",\"data\":null}");
                        })

                        // 已经登录，但是没有权限 -> 403
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("{\"code\":403,\"message\":\"权限不足\",\"data\":null}");
                        })
                )
                // 添加过滤
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
