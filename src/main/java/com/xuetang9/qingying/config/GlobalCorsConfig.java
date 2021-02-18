package com.xuetang9.qingying.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 全局跨域过滤器
 *
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/15 19:13
 * @copyright 老九学堂
 */
@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        // 创建CORS配置信息对象
        CorsConfiguration config = new CorsConfiguration();

        /***************设置跨域需要配置的属性***************/
        // 添加允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://www.qingying.com");
        // 添加允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 添加允许的头信息
        config.addAllowedHeader("*");
        // 设置否发送Cookie信息
        config.setAllowCredentials(true);
        /***************设置跨域需要配置的属性***************/

        // 添加映射路径，拦截所有请求
        UrlBasedCorsConfigurationSource configSource =
                new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        // 返回一个配置号的跨域过滤器
        return new CorsFilter(configSource);
    }
}
