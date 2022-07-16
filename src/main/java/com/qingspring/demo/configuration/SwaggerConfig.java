package com.qingspring.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <h3>qingspring</h3>
 * <p>SwaggerConfig</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-04 19:17
 **/

@Configuration
@EnableOpenApi
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("Ez")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingspring.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    @SuppressWarnings("all")
    public ApiInfo apiInfo(){
        return new ApiInfo(
                "Ez's api",
                "redis project",
                "v1.0",
                "2261839618@qq.com", //开发者团队的邮箱
                "Ez",
                "Apache 2.0",  //许可证
                "http://www.apache.org/licenses/LICENSE-2.0" //许可证链接
        );
    }
}
