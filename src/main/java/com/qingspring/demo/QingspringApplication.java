package com.qingspring.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author : EasyBreezyhs
* @date : 2022/07/02-14:29
*/
@SpringBootApplication
@MapperScan(basePackages = "com.qingspring.demo.mapper")
public class QingspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(QingspringApplication.class, args);
    }

}
