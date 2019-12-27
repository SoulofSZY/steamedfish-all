package com.spring.mybatis.plus.demo.loop;

import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/26
 * @since 1.0.0
 */
@Component
public class TestB {

    TestA testA;

    public TestB(TestA testA) {
        this.testA = testA;
    }
}