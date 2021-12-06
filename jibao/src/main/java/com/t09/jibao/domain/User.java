package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "password", length = 20)
    private String password;

    @CreatedDate
    @Column(name = "create_time")
    private Date create_time;

    @Column(name = "avatar_path", length = 60)
    private String avatar_path;

    @Column(name = "balance")
    private int balance;

    @Column(name = "active")
    private boolean is_active = false;

}

