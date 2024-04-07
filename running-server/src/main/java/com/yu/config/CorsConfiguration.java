//package com.yu.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class CorsConfiguration {
//
//    private static final String ALLOWED_ORIGINS = "*"; // 允许的域名，这里设置为所有域名
//    private static final String ALLOWED_METHODS = "GET, POST, PUT, DELETE, OPTIONS"; // 允许的HTTP方法
//    private static final String ALLOWED_HEADERS = "*"; // 允许的头部信息
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // 匹配所有请求路径
//                        .allowedOrigins(ALLOWED_ORIGINS) // 设置允许的域名
//                        .allowedMethods(ALLOWED_METHODS) // 设置允许的HTTP方法
//                        .allowedHeaders(ALLOWED_HEADERS); // 设置允许的头部信息
//            }
//        };
//    }
//}
