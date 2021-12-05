package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "administrator")
@Data
public class Administrator {

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
    @Column(name = "create_time", updatable = false, nullable = false)
    private Date create_time;

    @Column(name = "avatar_path", length = 40)
    private String avatar_path;

}

