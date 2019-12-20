package com.spring.tk.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * 〈person实体  〉
 *
 * @author steamedfish
 * @create 2019/12/3
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_person")
public class Person {

    private long id;
    private String name;
    private int age;
    private int gender;
}