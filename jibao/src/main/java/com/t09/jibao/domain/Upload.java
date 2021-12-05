package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "upload")
@Data
public class Upload implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(name = "name", length = 20)
    private String name;


    @ManyToOne
    @JoinColumn(name = "uid")
    User user;

    @Id
    @OneToOne
    @JoinColumn(name = "gid")
    Goods goods;

    @CreatedDate
    @Column(name = "upload_time")
    private Date upload_time;


}

