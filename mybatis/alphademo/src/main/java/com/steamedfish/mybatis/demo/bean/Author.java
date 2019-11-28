package com.steamedfish.mybatis.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * 〈作者〉
 *
 * @author steamedfish
 * @create 2019/11/28
 * @since 1.0.0
 */
@Alias("Author")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private long id;
    private String name;
    private int age;
}