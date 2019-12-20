package com.spring.tk.demo;

import com.alibaba.fastjson.JSON;
import com.spring.tk.demo.mapper.PersonMapper;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/12
 * @since 1.0.0
 */
public class TestTkMybatisSpringApp {

    private static Logger logger = Logger.getLogger(TestTkMybatisSpringApp.class);

    private static ApplicationContext context1() {
        return new ClassPathXmlApplicationContext("classpath:spring-context.xml");
    }

    public static void main(String[] args) {
        ApplicationContext context = context1();

        PersonMapper personMapper = context.getBean(PersonMapper.class);

        logger.info(JSON.toJSONString(personMapper.selectAll()));

    }
}