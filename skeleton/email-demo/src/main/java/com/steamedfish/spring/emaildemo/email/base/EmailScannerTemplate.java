package com.steamedfish.spring.emaildemo.email.base;

import com.alibaba.fastjson.JSON;
import com.steamedfish.spring.emaildemo.email.bean.EmailMsgBean;
import com.steamedfish.spring.emaildemo.email.bean.EmailSearchCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.*;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 〈邮件扫描模板〉
 *
 * @author sunzhengyu
 * @create 2019/12/27
 * @since 1.0.0
 */
@Slf4j
public abstract class EmailScannerTemplate {

    @Resource
    private MailProperties mailProperties;

    private Properties properties;

    //private Authenticator authenticator;

    @PostConstruct
    private void init() {
        properties = new Properties();
        properties.put("mail.pop3.ssl.enable", "true");
        properties.put("mail.imap.ssl.enable", "true");
//        authenticator = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
//            }
//        };
    }

    /**
     *
     * @param emailSearchCondition 自定义条件
     * @param checkEmailConsumer  自定义校验
     * @param parseAttachConsumer 针对附件的简单解析
     * @return List<EmailMsgBean> 解析后邮件基本信息
     */
    public List<EmailMsgBean> scan(EmailSearchCondition emailSearchCondition, Function<MimeMessageParser, Boolean> checkEmailConsumer
            , Consumer<InputStream> parseAttachConsumer) {

        Session session = Session.getDefaultInstance(properties);
        Store store = null;
        Folder folder = null;
        try {
            if ("pop3".equals(mailProperties.getProperties().get("scanner.protocal"))) {
                log.error("暂不兼容pop3协议!!");
                return new ArrayList<>();
            }
            //log.info(JSON.toJSONString(mailProperties));
            store = session.getStore(mailProperties.getProperties().get("scanner.protocal"));
            store.connect(mailProperties.getProperties().get("scanner.host"),
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

            SearchTerm searchTerm = parseSearchCondition(emailSearchCondition, mailProperties.getProperties().get("scanner.protocal"));

            Message[] messages;
            if (null == searchTerm) {
                messages = folder.getMessages();
            } else {
                messages = folder.search(searchTerm);
            }

            if (null == messages || messages.length == 0) {
                log.info("未获取到符合条件的邮件！params:{}", JSON.toJSONString(emailSearchCondition));
                return new ArrayList<>();
            }

            return parseMsg(messages, emailSearchCondition, checkEmailConsumer, parseAttachConsumer);
        } catch (Exception e) {
            log.info("系统异常", e);
        } finally {
            if (null != folder) {
                try {
                    // 参数false表示对邮件的修改不传送到服务器上
                    folder.close(true);
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
     * @param checkEmailConsumer 附件校验
     * @param parseAttachConsumer 针对附件的简单解析
     * @return List<EmailMsgBean> 解析后邮件基本信息
     */
    protected abstract List<EmailMsgBean> parseMsg(Message[] messages, EmailSearchCondition emailSearchCondition, Function<MimeMessageParser, Boolean> checkEmailConsumer
            , Consumer<InputStream> parseAttachConsumer);


    private SearchTerm parseSearchCondition(EmailSearchCondition condition, String protocol) {
        if (null == condition) {
            return null;
        }

        SearchTerm term = null;
        if (null != condition.getSubjects() && condition.getSubjects().length>0) {
            if (condition.getSubjects().length == 1) {
                term = new SubjectTerm(condition.getSubjects()[0]);
            } else {
                SubjectTerm[] subjectTerms = new SubjectTerm[condition.getSubjects().length];
                for (int i = 0; i < condition.getSubjects().length; i++) {
                    subjectTerms[i] = new SubjectTerm(condition.getSubjects()[i]);
                }
                term = new OrTerm(subjectTerms);
            }
        }

        if (null != condition.getFroms() && condition.getFroms().length>0) {
            SearchTerm fromTerm = null;
            if (condition.getFroms().length==1) {
                fromTerm =  new FromStringTerm(condition.getFroms()[0]);
            } else {
                FromStringTerm[] fromTerms = new FromStringTerm[condition.getFroms().length];
                for (int i = 0; i < condition.getFroms().length; i++) {
                    fromTerms[i] = new FromStringTerm(condition.getFroms()[i]);
                }
                fromTerm = new OrTerm(fromTerms);
            }
            if (term != null) {
                term = new AndTerm(term, fromTerm);
            } else {
                term = fromTerm;
            }
        }

        if (null != condition.getIntervalDays()) {
            LocalDate startDate = LocalDate.now().minusDays(Math.abs(condition.getIntervalDays()));
          //  LocalDate endDate = LocalDate.now().plusDays(1);
            ZoneId zone = ZoneId.systemDefault();
            Date start = Date.from(startDate.atStartOfDay().atZone(zone).toInstant());
       //     Date end = Date.from(endDate.atStartOfDay().atZone(zone).toInstant());

            SearchTerm dateTermGTFilter = "pop3".equals(protocol) ? new SentDateTerm(ComparisonTerm.GT, start) : new ReceivedDateTerm(ComparisonTerm.GT, start);
//            SearchTerm dateTermLTFilter = new SentDateTerm(ComparisonTerm.LT, end);
//            SearchTerm sendDateFilter = new AndTerm(dateTermGTFilter, dateTermLTFilter);
            if (term != null) {
                term = new AndTerm(term, dateTermGTFilter);
            } else {
                term = dateTermGTFilter;
            }
        }

//        if (condition.isFilterSeen() && "imap".equals(protocol)) {
//            FlagTerm flagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
//            if (term != null) {
//                term = new AndTerm(term, flagTerm);
//            } else {
//                term = flagTerm;
//            }
//        }
        return term;
    }
}