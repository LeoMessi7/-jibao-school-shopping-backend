package com.t09.jibao.service.implement;


import com.t09.jibao.Controller.Utils;
import com.t09.jibao.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

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

    /**
     * 2、发送带附件的邮件
     *
     * @param mailFrom     发件人
     * @param mailFromNick 发件人昵称
     * @param mailTo       收件人
     * @param cc           抄送人
     * @param subject
     * @param content
     * @param files
     */
    @Override
    public void sendMailWithAttachments(String mailFrom, String mailFromNick, String mailTo, String cc,
                                        String subject, String content, List<File> files) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            /*
            第二个参数true表示构造一个multipart message类型的邮件，multipart message类型的邮件包含多个正文、附件以及内嵌资源，
            邮件的表现形式更丰富
             */
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(mailFromNick + " <" + mailFrom + ">"));
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);

            // 设置多个收件人
            String[] toAddress = mailTo.split(",");
            mimeMessageHelper.setTo(toAddress);
            // 添加附件
            if (null != files) {
                for (File file : files) {
                    // 通过addAttachment方法添加附件
                    mimeMessageHelper.addAttachment(file.getName(), file);
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //发送邮件
        mailSender.send(mimeMessage);

    }

    /**
     * 3、正文内容带图片
     *
     * @param mailFrom
     * @param mailFromNick
     * @param mailTo
     * @param cc           抄送人
     * @param subject
     * @param content
     * @param imagePaths
     * @param imageId
     */
    @Override
    public void sendMailWithImage(String mailFrom, String mailFromNick, String mailTo, String cc, String subject,
                                  String content, String[] imagePaths, String[] imageId) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(mailFromNick + " <" + mailFrom + ">"));
            // 设置多个收件人
            String[] toAddress = mailTo.split(",");
            mimeMessageHelper.setTo(toAddress);
            mimeMessageHelper.setSubject(subject);
            // 第二个参数为true表示邮件正文是html格式的，默认是false
            mimeMessageHelper.setText(content, true);

            // 添加图片
            if (imagePaths != null && imagePaths.length != 0) {
                for (int i = 0; i < imagePaths.length; i++) {
                    // 通过FileSystemResource构造静态资源
                    FileSystemResource fileSystemResource = new FileSystemResource(imagePaths[i]);
                    // 调用addInline方法将资源加入邮件对象中
                    mimeMessageHelper.addInline(imageId[i], fileSystemResource);
                }
            }

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }

    /**
     * 4、使用Themeleaf构建邮件模板。需额外加spring-boot-starter-thymeleaf依赖
     *
     * @param mailFrom
     * @param mailFromNick
     * @param mailTo
     * @param cc
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlMailThymeLeaf(String mailFrom, String mailFromNick, String mailTo, String cc,
                                      String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(mailFromNick + " <" + mailFrom + ">"));
            // 设置多个收件人
            String[] toAddress = mailTo.split(",");
            mimeMessageHelper.setTo(toAddress);
            mimeMessageHelper.setSubject(subject);
            // 第二个参数为true表示邮件正文是html格式的，默认是false
            mimeMessageHelper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}