package com.qingspring.demo;

import com.qingspring.demo.controller.EchartsController;
import com.qingspring.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class QingspringApplicationTests {

    @Autowired
    DataSource dataSource;


    @Test
     void contextLoads() {

        //查看数据源
        System.out.println(dataSource.getClass());
        //获得数据库连接
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            //关闭连接
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
