package com.steamedfish.fastjson.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈作者〉
 *
 * @author steamedfish
 * @create 2019/12/4
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author<T> {

    private String name;
    private int age;

    private T model;
}