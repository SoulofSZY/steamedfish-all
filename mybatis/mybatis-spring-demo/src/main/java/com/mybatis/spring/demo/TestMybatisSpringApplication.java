package com.mybatis.spring.demo;

import com.alibaba.fastjson.JSON;
import com.mybatis.spring.demo.bean.Blog;
import com.mybatis.spring.demo.bean.Person;
import com.mybatis.spring.demo.conf.BaseConfiguration;
import com.mybatis.spring.demo.dao.BlogDao;
import com.mybatis.spring.demo.service.FooService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

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
        //FooService fooService = applicationContext().getBean(FooService.class);
        //System.out.println(JSON.toJSONString(fooService.findPeronByName("王五")));

       // fooService.insertPerson(Person.builder().id(3).name("丁宁").age(18).gender(1).build());

       BlogDao blogDao = applicationContext().getBean(BlogDao.class);

       // System.out.println(JSON.toJSONString(blogDao.getBlog(1)));

        List<Blog> blogList = new ArrayList<Blog>(){
            {
                add(Blog.builder().id(3L).title("test03").build());
                add(Blog.builder().id(4L).title("test04").build());
                add(Blog.builder().id(5L).title("test05").build());
            }
        };

        blogDao.insertBlogList(blogList);
    }
}