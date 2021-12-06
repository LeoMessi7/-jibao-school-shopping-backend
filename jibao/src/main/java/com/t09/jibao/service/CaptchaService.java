package com.t09.jibao.service;

import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;

public interface CaptchaService {
    Captcha save(Captcha captcha);
    Captcha findByUid(Long uid);
    Captcha createImageCaptcha(String image_captcha);
    Captcha createEmailCaptcha(User user, Captcha captcha, String email_captcha);
    Captcha findById(Long id);
    void deleteCaptcha(Long captcha_id);
}
