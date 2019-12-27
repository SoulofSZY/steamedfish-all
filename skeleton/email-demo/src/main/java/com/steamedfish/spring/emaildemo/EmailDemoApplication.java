package com.steamedfish.spring.emaildemo;

import com.alibaba.fastjson.JSON;
import com.steamedfish.spring.emaildemo.email.bean.EmailMsgBean;
import com.steamedfish.spring.emaildemo.email.bean.EmailSearchCondition;
import com.steamedfish.spring.emaildemo.email.handler.EmailScanner;
import com.steamedfish.spring.emaildemo.email.handler.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;

@SpringBootApplication
@Slf4j
public class EmailDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmailDemoApplication.class, args);
    }


    @Autowired
    EmailSender emailSender;

    @Autowired
    EmailScanner emailScanner;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        emailSender.send("hello邮件", "邮件0000000", new File("C:\\ztemp\\WJYX_20190901.csv")
//                , new File("C:\\ztemp\\WJYX_20190903.csv"));

        EmailSearchCondition emailSearchCondition = EmailSearchCondition.builder()
                .basePath("C:\\ztemp\\msg")
                .returnBaseInfo(true)
                .saveAttaches(true)
                .intervalDays(-1)
                .build();
        List<EmailMsgBean> msgBeans = emailScanner.scan(emailSearchCondition);
        log.info(JSON.toJSONString(msgBeans));
    }
}
