package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "feedback")
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "uid")
    User user;

    @Column(name = "content", length = 512)
    private String content;

    @CreatedDate
    @Column(name = "create_time")
    private Date create_time;



}

