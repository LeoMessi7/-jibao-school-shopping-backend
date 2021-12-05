package com.t09.jibao.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

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
    @Column(name = "dealt_time")
    private Date dealt_time;

    @Column(name = "content", length = 512)
    private String content;

    @Column(name = "status")
    private int status;

}

