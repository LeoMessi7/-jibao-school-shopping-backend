package com.t09.jibao.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "withdraw")
@Data
public class Withdraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uid")
    User user;

    @OneToOne
    @JoinColumn(name = "gid")
    Goods goods;


    @CreatedDate
    @Column(name = "withdraw_time")
    private Date withdraw_time;


}

