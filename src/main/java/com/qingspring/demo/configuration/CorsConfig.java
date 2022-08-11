package com.qingspring.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${system-params.web.address}")
    private String serverAddress;
    @Value("${system-params.web.port}")
    private String serverPort;


    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

//        "http://"+serverAddress+":"+serverPort

        //1 设置访问资源地址 前端地址
        corsConfiguration.addAllowedOrigin("http://"+this.serverAddress+":"+this.serverPort);
        //2 设置访问资源请求头
        corsConfiguration.addAllowedHeader("*");
        //3 设置访问资源请求方法
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**",corsConfiguration);

        return new CorsFilter(source);
    }
}


