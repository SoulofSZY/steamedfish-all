package com.steamedfish.spring.emaildemo.email.handler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ArrayUtil;
import com.steamedfish.spring.emaildemo.email.base.EmailScannerTemplate;
import com.steamedfish.spring.emaildemo.email.bean.EmailMsgBean;
import com.steamedfish.spring.emaildemo.email.bean.EmailSearchCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 〈Nx邮件扫描器〉
 *
 * @author steamedfish
 * @create 2019/12/27
 * @since 1.0.0
 */
@Component
@Slf4j
public class EmailScanner extends EmailScannerTemplate {

    @Override
    public List<EmailMsgBean> parseMsg(Message[] messages, EmailSearchCondition emailSearchCondition) {
        List<EmailMsgBean> emailInfoList = emailSearchCondition.isReturnBaseInfo() ? new ArrayList<>(messages.length) : null;
        for (Message message : messages) {
            try {
                MimeMessageParser parser = new MimeMessageParser((MimeMessage) message).parse();
                EmailMsgBean emailMsgBean = null;
                if (emailSearchCondition.isReturnBaseInfo()) {
                    emailMsgBean = EmailMsgBean.builder()
                            .from(parser.getFrom())
                            .subject(parser.getSubject())
                            .carbonCopyList(parser.getCc())
                            .sendToList(parser.getTo())
                            .htmlContent(parser.getHtmlContent())
                            .htmlContent(parser.getPlainContent())
                            .build();
                }
                if (emailSearchCondition.isSaveAttaches() && CollectionUtil.isNotEmpty(parser.getAttachmentList())) {
                    List<String> attacheFilePathes = null;
                    if (emailSearchCondition.isReturnBaseInfo()) {
                        attacheFilePathes = new ArrayList<>(parser.getAttachmentList().size());
                        emailMsgBean.setFilePath(attacheFilePathes);
                    }
                    for (DataSource ds : parser.getAttachmentList()) {
                        String fileName = emailSearchCondition.getBasePath() + File.separator + ds.getName();
                        if (emailSearchCondition.isReturnBaseInfo()) {
                            attacheFilePathes.add(fileName);
                        }
                        try (BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(fileName))
                             ; BufferedInputStream ins =  new BufferedInputStream(ds.getInputStream())){

                            IoUtil.copy(ins, outStream);
                           log.info("附件:" + fileName);
                        }
                    }

                }
            } catch (Exception e) {
                log.error("邮件解析异常");
            }
        }

        return emailInfoList;
    }


}