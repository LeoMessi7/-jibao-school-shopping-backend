package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "withdraw")
@Data
public class Withdraw implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @OneToOne
    @JoinColumn(name = "gid")
    Goods goods;

    @Column(name = "name", length = 20)
    private String name;


    @ManyToOne
    @JoinColumn(name = "uid")
    User user;

    @CreatedDate
    @Column(name = "withdraw_time")
    private Date withdraw_time;


}

