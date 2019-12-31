package com.steamedfish.spring.emaildemo.email.bean;

import lombok.Builder;
import lombok.Data;

/**
 * 〈扫描收件箱过滤条件〉
 *
 * @author sunzhengyu
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
     * 发件人
     */
    private String[] froms;

    /**
     * 间隔天数 目前只支持从当前日期往过去日期数  例如 -1 ：昨天到现在   pop3 - SentDateTerm  imap - ReceivedDateTerm
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

    /**
     * 是否简单处理附件 单个处理 不是组合处理
     */
    private boolean dealAttach;

//    /**
//     *  是否有附件 过滤
//     */
//    private boolean withAttaches;

//    /**
//     * 是否过滤 已读文件 imap 协议生效 pop3不生效  实际tx邮箱未生效
//     */
//    private boolean filterSeen;
}