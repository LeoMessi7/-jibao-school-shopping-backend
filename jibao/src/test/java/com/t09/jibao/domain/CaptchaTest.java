package com.t09.jibao.domain;

import com.t09.jibao.service.CaptchaService;
import com.t09.jibao.service.CategoryService;
import com.t09.jibao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class CaptchaTest {
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private UserService userService;
    @Test
    void testInsert(){
        Captcha captcha = new Captcha();
        captcha.setEmail_captcha("123456");
        captcha.setImage_captcha("1234");
        captcha.setUser(userService.findById(1L));
        captcha.setCreate_time(new Date());
        captchaService.save(captcha);
    }

    @Test
    void testQuery(){
        System.out.println(captchaService.findByUid(1L).create_time);
    }
}
