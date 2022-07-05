package com.qingspring.demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <h3>qingspring</h3>
 * <p>跨域</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-04 15:44
 **/
@Configuration
public class CorsConfig {

    /**
    * @description :  当前跨域请求最大有效时长 此处为一天
    */
    private static final long MAX_AGE = 24 * 60 * 60;

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //1 设置访问资源地址
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        //2 设置访问资源请求头
        corsConfiguration.addAllowedHeader("*");
        //3 设置访问资源请求方法
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**",corsConfiguration);

        return new CorsFilter(source);
    }
}


