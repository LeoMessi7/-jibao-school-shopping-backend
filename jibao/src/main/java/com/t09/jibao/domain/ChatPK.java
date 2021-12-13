package com.t09.jibao.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class ChatPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "senderId")
    User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    User receiver;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "chatTime")
    private Date chatTime;
}
