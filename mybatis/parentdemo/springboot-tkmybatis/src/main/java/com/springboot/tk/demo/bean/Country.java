package com.springboot.tk.demo.bean;

import lombok.Data;

import javax.persistence.Id;

/**
 * 〈测试〉
 *
 * @author steamedfish
 * @create 2019/12/20
 * @since 1.0.0
 */
@Data
public class Country {
    @Id
    private Integer id;
    private String  countryname;
    private String  countrycode;
}