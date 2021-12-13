package com.t09.jibao.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity(name = "captcha")
@Data
public class Captcha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "uid")
    User user;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "create_time")
    Date create_time;

    @Column(name = "image_captcha", length = 10)
    private String image_captcha;
    @Column(name = "email_captcha", length = 10)
    private String email_captcha;


}

