package com.t09.jibao.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

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
    @Column(name = "comment_time")
    private Date comment_time;

    @Column(name = "content", length = 512)
    private String content;

    @Column(name = "mark")
    private double mark;

}

