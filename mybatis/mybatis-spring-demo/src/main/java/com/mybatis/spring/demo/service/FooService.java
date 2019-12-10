package com.mybatis.spring.demo.service;

import com.mybatis.spring.demo.bean.Person;

import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/10
 * @since 1.0.0
 */
public interface FooService {

    List<Person> findPeronByName(String name);
}