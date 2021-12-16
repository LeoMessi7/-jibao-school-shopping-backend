package com.t09.jibao.service;

import java.io.File;
import java.util.List;

public interface MailService {


    // send captcha
    void sendCaptchaMail(String mailFromNick, String mailTo, String subject, String content);

    // send feedback
    void sendFeedbackMail(String mailFromNick, String mailTo, String subject, String content);

}