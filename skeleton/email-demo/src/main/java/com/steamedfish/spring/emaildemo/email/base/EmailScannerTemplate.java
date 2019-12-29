package com.steamedfish.spring.emaildemo.email.base;

import com.alibaba.fastjson.JSON;
import com.steamedfish.spring.emaildemo.email.bean.EmailMsgBean;
import com.steamedfish.spring.emaildemo.email.bean.EmailSearchCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.search.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 〈邮件扫描模板〉
 *
 * @author steamedfish
 * @create 2019/12/27
 * @since 1.0.0
 */
@Slf4j
public abstract class EmailScannerTemplate {

    @Resource
    private MailProperties mailProperties;

    private Properties properties;

    private Authenticator authenticator;

    @PostConstruct
    private void init() {
        properties = new Properties();
        //properties.setProperty("mail.pop3.host", mailProperties.getProperties().get("scanner.host"));
        //properties.setProperty("mail.pop3.port", mailProperties.getProperties().get("scanner.port"));
        //properties.setProperty("mail.store.protocol", mailProperties.getProperties().get("scanner.protocal"));
       // properties.put("mail.store.protocol", "imap");
       // properties.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
        authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
            }
        };
    }

    public List<EmailMsgBean> scan(EmailSearchCondition emailSearchCondition) {

        Session session = Session.getDefaultInstance(properties);
        //Session session = Session.getDefaultInstance(properties);
        Store store = null;
        Folder folder = null;
        try {
            store = session.getStore(mailProperties.getProperties().get("scanner.protocal"));
            store.connect(mailProperties.getProperties().get("scanner.host"),
                    Integer.parseInt(mailProperties.getProperties().get("scanner.port")),
                    mailProperties.getUsername(),
                    mailProperties.getPassword());
            // 获取收件箱
            folder = store.getFolder("INBOX");
            if (null == folder) {
                log.warn("获取邮箱文件信息为空");
                return new ArrayList<>();
            }
            // 设置对邮件帐户的访问权限可以读写
            folder.open(Folder.READ_WRITE);


            SearchTerm searchTerm = parseSearchCondition(emailSearchCondition);

            Message[] messages;
            if (null == searchTerm) {
                messages = folder.getMessages();
            } else {
                messages = folder.search(searchTerm);
            }

            if (null == messages || messages.length == 0) {
                log.info("未获取到符合条件的邮件！params:{}", JSON.toJSONString(emailSearchCondition));
            }

            return parseMsg(messages, emailSearchCondition);

        } catch (Exception e) {
            log.info("系统异常", e);
        } finally {
            if (null != folder) {
                try {
                    // 参数false表示对邮件的修改不传送到服务器上
                    folder.close(false);
                } catch (Exception e) {
                    log.info("系统异常", e);
                }

            }

            if (null != store) {
                try {
                    store.close();
                } catch (Exception e) {
                    log.info("系统异常", e);
                }
            }
        }

        return new ArrayList<>();
    }

    /**
     * 处理具体过滤的邮件
     *
     * @param messages
     * @param emailSearchCondition 自定义条件
     * @return List<EmailMsgBean> 解析后邮件基本信息
     */
    public abstract List<EmailMsgBean> parseMsg(Message[] messages, EmailSearchCondition emailSearchCondition);


    private SearchTerm parseSearchCondition(EmailSearchCondition condition) {
        if (null == condition) {
            return null;
        }

        SearchTerm dateTermGTFilter = null;
        if (null != condition.getIntervalDays()) {
            LocalDate startDate = LocalDate.now().minusDays(Math.abs(condition.getIntervalDays()));
            ZoneId zone = ZoneId.systemDefault();
            Date date = Date.from(startDate.atStartOfDay().atZone(zone).toInstant());
            dateTermGTFilter = new SentDateTerm(ComparisonTerm.GT, date);
        }

        SearchTerm subjectFilter = null;
        if (null != condition.getSubjects()) {
            if (condition.getSubjects().length == 1) {
                subjectFilter = new SubjectTerm(condition.getSubjects()[0]);
            } else {
                SubjectTerm[] subjectTerms = new SubjectTerm[condition.getSubjects().length];
                for (int i = 0; i < condition.getSubjects().length; i++) {
                    subjectTerms[i] = new SubjectTerm(condition.getSubjects()[i]);
                }
                subjectFilter = new OrTerm(subjectTerms);
            }
        }


        if (null != dateTermGTFilter && null != subjectFilter) {
            return new AndTerm(dateTermGTFilter, subjectFilter);
        }

        return null != dateTermGTFilter ? dateTermGTFilter : subjectFilter;
    }
}