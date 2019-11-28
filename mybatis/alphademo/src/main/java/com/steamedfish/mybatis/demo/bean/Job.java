/**
 * Copyright (C), 2019-2019
 */
package com.steamedfish.mybatis.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/11/28
 * @since 1.0.0
 */
@Alias("job")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    /**
     * 职业类型
     */
    private String jobType;
    /**
     * 公司
     */
    private String company;
    /**
     * 公司地点
     */
    private String address;
}
