package com.steamedfish.spring.emaildemo.email.handler;

import cn.hutool.core.lang.Assert;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 〈邮件发送〉
 *
 * @author sunzhengyu
 * @create 2019/12/27
 * @since 1.0.0
 */
@Lazy
@Component
@Slf4j
public class EmailSender {

    @Resource
    private JavaMailSender mailSender;
    @Resource
    private MailProperties mailProperties;


    public void send(@NotNull String title, @NotNull String content, File... attaches) {
        Assert.notBlank(title);
        Assert.notBlank(content);
        Assert.notBlank(mailProperties.getProperties().get("toEmail"));

        Set<String> emailSet = new HashSet<>(Arrays.asList(mailProperties.getProperties().get("toEmail").split(",")));
        StopWatch stopWatch = new StopWatch("email-send-task");
        stopWatch.start();
        for (String email : emailSet) {
            try {
                // 组装发送邮件
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(mailProperties.getUsername(), mailProperties.getProperties().get("fromPersonal"));
                helper.setTo(email);
                helper.setSubject(title);
                helper.setText(content, true);

                //设置附件
                if (null != attaches) {
                    for (File attach : attaches) {
                        if (!attach.exists()) {
                            log.warn("文件：{}-不存在", attach.getAbsolutePath());
                            continue;
                        }
                        //创建附件
                        MimeBodyPart emailAttach = new MimeBodyPart();
                        //构造附件的数据源
                        FileDataSource fds = new FileDataSource(attach);
                        //数据处理
                        DataHandler dh = new DataHandler(fds);
                        //设置附件一的数据源
                        emailAttach.setDataHandler(dh);
                        //设置附件一的文件名
                        emailAttach.setFileName(MimeUtility.encodeText(attach.getName()));
                        helper.getRootMimeMultipart().addBodyPart(emailAttach);
                    }
                }
                mailSender.send(mimeMessage);
            } catch (Exception e) {
               log.error("系统异常",e);
            }
        }
        stopWatch.stop();
        log.info(stopWatch.shortSummary());
    }
}