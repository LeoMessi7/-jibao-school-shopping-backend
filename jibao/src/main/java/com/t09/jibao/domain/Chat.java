package com.t09.jibao.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "chat")
@Data
public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    ChatPK pk;

    @Column(name = "content", length = 512)
    private String content;

}

