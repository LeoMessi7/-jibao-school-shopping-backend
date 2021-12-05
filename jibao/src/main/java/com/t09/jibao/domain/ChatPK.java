package com.t09.jibao.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

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
    @JoinColumn(name = "sender_id")
    User buyer;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    User receiver;

    @CreatedDate
    @Column(name = "comment_time")
    private Date comment_time;
}
