package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Many;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "select")
@Data
public class Select implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "uid")
    User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "gid")
    Goods goods;

    @CreatedDate
    @Column(name = "select_time")
    private Date select_time;


}

