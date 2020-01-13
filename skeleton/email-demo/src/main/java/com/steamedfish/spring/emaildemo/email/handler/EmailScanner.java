package com.steamedfish.spring.emaildemo.email.handler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import com.steamedfish.spring.emaildemo.email.base.EmailScannerTemplate;
import com.steamedfish.spring.emaildemo.email.bean.EmailMsgBean;
import com.steamedfish.spring.emaildemo.email.bean.EmailSearchCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 〈Nx邮件扫描器〉
 *
 * @author sunzhengyu
 * @create 2019/12/27
 * @since 1.0.0
 */
@Component
@Lazy
@Slf4j
public class EmailScanner extends EmailScannerTemplate {



    @Override
    public List<EmailMsgBean> parseMsg(Message[] messages, EmailSearchCondition emailSearchCondition, Function<MimeMessageParser, Boolean> checkAttachConsumer
            , Consumer<InputStream> parseAttachConsumer) {
        List<EmailMsgBean> emailInfoList = emailSearchCondition.isReturnBaseInfo() ? new ArrayList<>(messages.length) : null;

        for (Message message : messages) {
            try {

               // log.info(JSON.toJSONString(message));
                MimeMessageParser parser = new MimeMessageParser((MimeMessage) message).parse();
                if (!checkAttachConsumer.apply(parser)) {
                    log.info("自定义校验失败，邮件不处理：{}", parser.getSubject());
                    continue;
                }

                log.info(message.getFlags().toString());
                EmailMsgBean emailMsgBean = null;
                if (emailSearchCondition.isReturnBaseInfo()) {
                    emailMsgBean = EmailMsgBean.builder()
                            .from(parser.getFrom())
                            .subject(parser.getSubject())
                            .carbonCopyList(parser.getCc())
                            .sendToList(parser.getTo())
                            .htmlContent(parser.getHtmlContent())
                            .plainContent(parser.getPlainContent())
                            .build();
                    emailInfoList.add(emailMsgBean);
                }
                // imap下载了附件会主动标记为已读
                if (CollectionUtil.isNotEmpty(parser.getAttachmentList())) {
                    List<String> attacheFilePathes = null;
                    if (emailSearchCondition.isSaveAttaches() && emailSearchCondition.isReturnBaseInfo()) {
                        attacheFilePathes = new ArrayList<>(parser.getAttachmentList().size());
                        emailMsgBean.setFilePath(attacheFilePathes);
                    }
                    if (emailSearchCondition.isSaveAttaches() || emailSearchCondition.isDealAttach()) {
                        for (DataSource ds : parser.getAttachmentList()) {
                            try (InputStream ins = ds.getInputStream()) {
                                if (emailSearchCondition.isSaveAttaches()) {
                                    String fileName = emailSearchCondition.getBasePath() + File.separator + ds.getName();
                                    if (emailSearchCondition.isReturnBaseInfo()) {
                                        attacheFilePathes.add(fileName);
                                    }
                                    try (BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(fileName))){
                                        IoUtil.copy(ins, outStream);
                                        log.info("附件:" + fileName);
                                    } catch (Exception e) {
                                        log.error("保存附件失败：params[subject:{}; from:{}; filename:{}]", parser.getSubject(), fileName, e);
                                    }
                                }

                                if (emailSearchCondition.isDealAttach()) {
                                    // 解析附件
                                    if (emailSearchCondition.isSaveAttaches()) {
                                        ins.reset();
                                    }
                                    parseAttachConsumer.accept(ins);
                                }
                            } catch (Exception e) {
                                log.error("解析邮件附件失败：params[subject:{}; from:{}]", parser.getSubject(), parser.getFrom(),e);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.error("邮件解析异常", e);
            }
        }

        return emailInfoList;
    }


}