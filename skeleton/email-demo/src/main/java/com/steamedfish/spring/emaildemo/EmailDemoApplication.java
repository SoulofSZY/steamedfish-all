package com.steamedfish.spring.emaildemo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
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

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
//        test();
        EmailSearchCondition emailSearchCondition = EmailSearchCondition.builder()
                .basePath("C:\\ztemp\\msg")
                .returnBaseInfo(true)
                .saveAttaches(true)
                .dealAttach(true)
                //.filterSeen(true)
                .intervalDays(-4)
                .build();

        List<EmailMsgBean> msgBeans = emailScanner.scan(emailSearchCondition, (email) -> {
            // 自定义邮件筛选
            String subject;
            Date receivedDate;
            String receivedDateStr;
            try {
                subject = email.getSubject();
                receivedDate = email.getMimeMessage().getReceivedDate();
                receivedDateStr = DateUtil.format(receivedDate, "yyyy-MM-dd HH:mm:ss");
            } catch (Exception e) {
                log.error("获取邮件基本信息异常", e);
                return false;
            }
            log.debug(subject + ":" + receivedDateStr);
            return true;
        }, (in) -> {
            // 解析csv 调用tx接口 无需关闭流
            CsvReader reader = CsvUtil.getReader();
            CsvData csvData = reader.read(new BufferedReader(new InputStreamReader(in)));
            //遍历行
            List<CsvRow> rows = csvData.getRows();
            if (CollectionUtil.isEmpty(rows)) {
                log.info("附件无信息");
                return;
            }
            for (int i = 0; i < rows.size(); i++) {
                List<String> rowList = rows.get(i).getRawList();
                if (i == 0 && "子项目ID".equals(rowList.get(0))) {
                    continue;
                }
                //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
                log.debug("rowInfo:{}", JSON.toJSONString(rowList));
            }
        });
        log.info(JSON.toJSONString(msgBeans));
    }


    private void test() throws Exception {
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");//指定邮件发送协议  只接受邮件是可以不要写的
        props.put("mail.store.protocol", "imap");    //指定邮件接收协议
        props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");//指定支持SMTP协议的Transport具体类，允许由第三方提供
        props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");    //指定支持IMAP协议的Store具体类，允许由第三方提供
        //以上这4个可以全部写上
        props.put("mail.smtp.host", "pop.qq.com");//指定采用SMTP协议的邮件发送服务器的IP地址或主机名

        //注意你的服务器的地址
        //网易一共有这三个地址   pop.163.com  imap.163.com  stmp.163.com

        Session session = Session.getInstance(props);// 设置环境信息
        Store store = session.getStore("pop3");//指定接收邮件协议

        store.connect("pop.qq.com", "154962931@qq.com", "dsbvnflvaxuvcbdf");
        //            网易邮箱的服务器地址   账号  密码

        //获得名为默认"inbox"的邮件夹当你自己有定义其他的邮件夹也可以写上去
        Folder folder = store.getFolder("inbox");

        //打开邮件夹
        folder.open(Folder.READ_ONLY);//它是一个邮件文件夹类。Folder类有两个常见的属性，READ_ONLY表示只读，READ_WRITE表示其内容可读可写

        //获得邮件夹中的邮件数目
        System.out.println("你一共有:" + folder.getMessageCount() + " 封邮件");
        //获得邮件夹中的未读邮件数目

        System.out.println("你一共有:" + folder.getUnreadMessageCount() + " 未读邮件");
        System.out.println("你一共删除了 " + folder.getDeletedMessageCount() + " 封邮件");

        for (int i = 1; i <= folder.getMessageCount(); i++) {

            Message msg = folder.getMessage(i);
            System.out.println("========================================");
            //获得邮件的发送者、主题和正文
            if (msg.getFrom()[0].toString().contains("<")) {
                System.out.println("邮件来自:" + msg.getFrom()[0].toString().substring(msg.getFrom()[0].toString().lastIndexOf("<") + 1, msg.getFrom()[0].toString().lastIndexOf(">")));
            } else {
                System.out.println("邮件来自:" + msg.getFrom()[0]);
            }
            System.out.println("邮件主题:" + msg.getSubject());
            System.out.println("发送日期:" + msg.getSentDate());
            String type = msg.getContentType().toString().substring(0, msg.getContentType().toString().indexOf(";"));
            System.out.println("邮件类型:" + type);
            System.out.println("邮件内容:" + msg.getContentType().toString());//multipart  当文件类型为multipart/* 时不能正确显示
            if (type.equals("text/html")) {
                //请你根据文件的类型来更改文件的解析方式  text/html  multipart/alternative表示复合类型
            }
            InternetAddress[] address = (InternetAddress[]) msg.getFrom();
            String from = address[0].getAddress();//这个是发邮件人的地址
            if (from == null) {
                from = "";
            }
            String personal = address[0].getPersonal();//这个是发邮件的人的姓名
            if (personal == null) {
                personal = "";
            }
            String fromaddr = personal + "<" + from + ">";
            System.out.println("邮件作者：" + fromaddr);
            System.out.println("========================================\r\n");
        }

    }
}
