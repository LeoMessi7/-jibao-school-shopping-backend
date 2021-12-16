package com.t09.jibao.service.implement;


import com.t09.jibao.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    public void sendCaptchaMail(String mailFromNick, String mailTo, String subject, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(new InternetAddress(mailFromNick + " <" + mailFrom + ">"));
            mimeMessageHelper.setTo(mailTo);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("failed：", e);
        }
    }



    @Override
    public void sendFeedbackMail(String mailFromNick, String mailTo, String subject, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(new InternetAddress(mailFromNick + " <" + mailFrom + ">"));
            mimeMessageHelper.setTo(mailTo);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("failed：", e);
        }
    }

}