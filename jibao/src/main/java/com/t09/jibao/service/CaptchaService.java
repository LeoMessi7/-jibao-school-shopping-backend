package com.t09.jibao.service;

import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;

public interface CaptchaService {
    // save
    Captcha save(Captcha captcha);

    // find by user id
    Captcha findByUid(Long uid);

    // create image captcha
    Captcha createImageCaptcha(String image_captcha);

    // create email captcha
    Captcha createEmailCaptcha(User user, String email_captcha);

    // find by image id
    Captcha findById(Long id);

    // delete captcha
    void deleteCaptcha(Long captcha_id);
}
