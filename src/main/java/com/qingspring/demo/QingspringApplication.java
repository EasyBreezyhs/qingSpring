package com.qingspring.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* @author : EasyBreezyhs
* @date : 2022/07/02-14:29
*/
@SpringBootApplication
@EnableSwagger2
public class QingspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(QingspringApplication.class, args);
    }
}
