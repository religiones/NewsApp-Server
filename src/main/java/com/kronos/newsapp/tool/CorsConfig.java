package com.kronos.newsapp.tool;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")  // 项目中的所有接口支持跨域
                .allowedOriginPatterns("*")    // 支持跨域的地址
                .allowCredentials(true)
                .allowedMethods("*")    // 跨域方法
                .maxAge(3600);  // 跨域允许时间
    }
}
