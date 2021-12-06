package com.t09.jibao.service.implement;

import com.t09.jibao.dao.CaptchaDAO;
import com.t09.jibao.dao.CategoryDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaDAO captchaDAO;

    @Autowired
    private UserDAO userDAO;

    public Captcha findByUid(Long uid){
        User user = userDAO.findById(uid).get();
        return captchaDAO.findCaptchaByUser(user).get(0);
    }
    public Captcha save(Captcha captcha){
        return captchaDAO.save(captcha);
    }

    public Captcha createImageCaptcha(String image_captcha){
        Captcha captcha = new Captcha();
        captcha.setCreate_time(new Date());
        captcha.setImage_captcha(image_captcha);
        return save(captcha);
    }
    public void deleteCaptcha(Long image_id){
        Captcha captcha = captchaDAO.findById(image_id).get();
        captchaDAO.delete(captcha);
    }

    public Captcha findById(Long image_id){
        Captcha captcha = captchaDAO.findById(image_id).get();
        return save(captcha);
    }

    public Captcha createEmailCaptcha(User user, Captcha captcha, String email_captcha){
        captcha.setUser(user);
        captcha.setEmail_captcha(email_captcha);
        captcha.setCreate_time(new Date());
        return save(captcha);
    }
}
