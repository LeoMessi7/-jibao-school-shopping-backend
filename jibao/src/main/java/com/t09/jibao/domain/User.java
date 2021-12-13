package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
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
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "avatarPath", length = 60)
    private String avatarPath;

    @Column(name = "balance")
    private int balance;

    @Column(name = "active")
    private boolean active = false;


}

