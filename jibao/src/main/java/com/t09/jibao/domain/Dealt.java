package com.t09.jibao.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "dealt")
@Data
public class Dealt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fid")
    Feedback feedback;

    @ManyToOne
    @JoinColumn(name = "aid")
    Administrator administrator;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "dealtTime")
    private Date dealtTime;

    @Column(name = "content", length = 512)
    private String content;

}

