package com.steamedfish.mybatis.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈实体〉
 *
 * @author steamedfish
 * @create 2019/11/28
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private Long id;
    private String title;
}