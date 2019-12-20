package com.springboot.tk.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 〈启动类〉
 *
 * @author steamedfish
 * @create 2019/12/20
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan({"com.springboot.tk.demo.mapper"})
public class DemoApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }


}