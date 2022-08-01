package com.qingspring.demo.configuration;

import com.qingspring.demo.utils.JWT.JwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h3>qingspring</h3>
 * <p>InterceptorConfig (JWT)</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-27 19:22
 **/

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")         //拦截所有请求
                .excludePathPatterns("/user/login","/user/register",
                        "/**/*.js",              //js静态资源
                        "/**/*.css",             //css静态资源
                        "/**/*.woff",
                        "/**/*.ttf",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.jepg",
                        "/swagger-ui.html");
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }



//    @Value("${accessFile.resourceHandler}")
//    private String resourceHandler; //匹配url 中的资源映射
//
//    @Value("${accessFile.location}")
//    private String location; //上传文件保存的本地目录
//
        @Value("${files.upload.path}")
        private String fileUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //匹配到resourceHandler,将URL映射至location,也就是本地文件夹
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + fileUploadPath);
    }



}
