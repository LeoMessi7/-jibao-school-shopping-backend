package com.t09.jibao.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "comment")
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    CommentPK pk;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "commentTime")
    private Date commentTime;

    @Column(name = "content", length = 512)
    private String content;

    @Column(name = "mark")
    private double mark;

}

