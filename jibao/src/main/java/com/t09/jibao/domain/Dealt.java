package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Many;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "dealt")
@Data
public class Dealt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
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

