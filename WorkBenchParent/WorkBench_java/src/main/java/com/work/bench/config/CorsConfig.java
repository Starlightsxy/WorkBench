package com.work.bench.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/** 跨域配置
 * @author 洁心未眠
 * @Package com.work.bench.config
 * @date 2026/8/10 20:42
 */
@Configuration
public class CorsConfig {


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();


        // 允许所有来源
        config.setAllowedOriginPatterns(
                List.of("*")
        );


        // 允许所有请求方式
        config.setAllowedMethods(
                List.of("*")
        );


        // 允许所有请求头
        config.setAllowedHeaders(
                List.of("*")
        );


        // 允许携带 token
        config.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();


        source.registerCorsConfiguration(
                "/**",
                config
        );


        return source;
    }
}