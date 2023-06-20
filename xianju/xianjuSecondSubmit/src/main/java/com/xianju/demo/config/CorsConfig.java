package com.xianju.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域过滤器
 * @author owen
 * @since 2022/5/1 20:53
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域的路径
        registry.addMapping("/**")
                .allowedOriginPatterns("*")// 允许跨域请求的域名
                .allowCredentials(true)   // 是否允许cookie
                .allowedMethods("GET", "POST", "DELETE", "PUT") // 允许的请求方式
                .allowedHeaders("*") // 允许的header属性
                .maxAge(3600);// 跨域允许时间
    }

}


