package com.qingspring.demo.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * <h3>Qingdemo</h3>
 * <p>MyBatisplus-generate</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-07 19:02
 **/
public class CodeGenerate {


    private static void gennerate(){
        FastAutoGenerator.create("jdbc:mysql://114.132.161.177:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false", "root", "hhs2022")
                .globalConfig(builder -> {
                    builder.author("EasyBreezyhs") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\Tools\\Project\\Java\\qingSpring\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.qingspring.demo") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Tools\\Project\\Java\\qingSpring\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {

                    builder.entityBuilder().enableLombok().idType(IdType.AUTO);//开启lombook
//                    builder.mapperBuilder().enableMapperAnnotation().build();   //开启mapper注解  有@MapperScan不用
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器


                    builder.addInclude("t_article") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀

                    builder.mapperBuilder().enableBaseResultMap();

                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }


//    public static void main(String[] args) {
//        gennerate();
//    }


}
