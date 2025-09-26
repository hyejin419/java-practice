package com.koreait.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /uploads/** 요청이 오면 실제 파일 시스템의 /uploads/ 폴더에서 제공
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/"); // ← 프로젝트 루트 기준 경로
    }
}