package com.mybatis.spring.demo;

import com.alibaba.fastjson.JSON;
import com.mybatis.spring.demo.conf.BaseConfiguration;
import com.mybatis.spring.demo.service.FooService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 〈测试〉
 *
 * @author steamedfish
 * @create 2019/12/10
 * @since 1.0.0
 */
public class TestMybatisSpringApplication {

    public static ApplicationContext applicationContext(){

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        return context;
    }

    public static ApplicationContext applicationContext2() {
        return new AnnotationConfigApplicationContext(BaseConfiguration.class);
    }

    public static void main(String[] args) {
        FooService fooService = applicationContext2().getBean(FooService.class);
        System.out.println(JSON.toJSONString(fooService.findPeronByName("王五")));
    }
}