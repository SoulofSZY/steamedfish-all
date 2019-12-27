package com.mybatis.spring.demo.loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/26
 * @since 1.0.0
 */
@Component
public class TestA {

    @Autowired
    TestB testB;
}