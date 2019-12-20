package com.spring.mybatis.plus.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/20
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan({"com.spring.mybatis.plus.demo.mapper"})
public class QuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);
    }
}