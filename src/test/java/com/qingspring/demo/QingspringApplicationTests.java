package com.qingspring.demo;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.qingspring.demo.controller.EchartsController;
import com.qingspring.demo.controller.UserController;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest
class QingspringApplicationTests {

    @Autowired
    DataSource dataSource;



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
    @Test
    void testMongo(){
//        List<ServerAddress> adds = new ArrayList<>();
//        ServerAddress serverAddress = new ServerAddress("114.132.161.177", 27017);
//        adds.add(serverAddress);
//
//        MongoCredential credential = MongoCredential.createCredential("qing", "qing", "123456".toCharArray());
//        MongoClient mongoClient = new MongoClient(adds, Collections.singletonList(credential));
//        以上是用户密码
//        没有密码直接登录
//        MongoClient mongoClient = new MongoClient("114.132.161.177:27017");


        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://qing:123456@114.132.161.177:27017/qing");
        MongoClient mongoClient = new MongoClient(mongoClientURI);

        MongoDatabase qing = mongoClient.getDatabase("qing");

        MongoCollection<Document> test = qing.getCollection("test");

        FindIterable<Document> documents = test.find();

        for (Document document : documents) {
            System.out.println("_id：" + document.get("_id"));
            System.out.println("内容：" + document.get("content"));
            System.out.println("用户ID:" + document.get("userid"));
            System.out.println("点赞数：" + document.get("thumbup"));
        }

//        增加
        Map<String,Object> map = new HashMap();
        map.put("_id", "6");
        map.put("content", "很棒！");
        map.put("userid", "9999");
        map.put("thumbup", 123);

        Document document1 = new Document(map);

        test.insertOne(document1);

        System.out.println("新增一条"+test.find(new BasicDBObject("_id","6")));

        Bson filter = new BasicDBObject("_id","6");
        Bson update = new BasicDBObject("$set",new Document("userod","8888"));

        test.updateOne(filter,update);
        System.out.println("修改一条"+test.find(new BasicDBObject("_id","6")));
        //

        test.deleteOne(filter);
        System.out.println("删除一条"+test.find(new BasicDBObject("_id","6")));

        mongoClient.close();

    }



}
