package com.kronos.newsapp.tool;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置图片静态资源处理器
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:E:/Java Projects/NewsApp/images/");
    }
}

