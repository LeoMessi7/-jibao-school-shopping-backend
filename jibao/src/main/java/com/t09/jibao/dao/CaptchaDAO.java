package com.t09.jibao.dao;

import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaptchaDAO extends JpaRepository<Captcha, Long> {
    List<Captcha> findCaptchaByUser(User user);

}