package com.steamedfish.spring.emaildemo.email.bean;

import lombok.Builder;
import lombok.Data;

/**
 * 〈扫描收件箱过滤条件〉
 *
 * @author steamedfish
 * @create 2019/12/27
 * @since 1.0.0
 */
@Data
@Builder
public class EmailSearchCondition {

    /**
     * 邮件标题 完全匹配
     */
    private String[] subjects;

    /**
     * 间隔天数 目前只支持从当前日期往过去日期数  例如 -1 ：昨天到现在
     */
    private Integer intervalDays;

    /**
     * 邮件附件存储路径
     */
    private String basePath;

    /**
     * 是否返回邮件基本信息
     */
    private boolean returnBaseInfo;

    /**
     * 是否保存附件
     */
    private boolean saveAttaches;
}