package com.steamedfish.spring.emaildemo.email.bean;

import lombok.Builder;
import lombok.Data;

import javax.mail.Address;
import java.util.List;

/**
 * 〈邮件基本信息〉
 *
 * @author steamedfish
 * @create 2019/12/27
 * @since 1.0.0
 */
@Data
@Builder
public class EmailMsgBean {

    /**
     * 获取发件人地址
     */
    private String from;

    /**
     * 抄送人地址
     */
    private List<Address> carbonCopyList;

    /**
     * 收件人地址
     */
    private List<Address> sendToList;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * Html内容
     */
    private String htmlContent;

    /**
     * 纯文本邮件内容（注：有些邮件不支持html）
     */
    private String plainContent;

    /**
     * 附件保存
     */
    private List<String> filePath;
}