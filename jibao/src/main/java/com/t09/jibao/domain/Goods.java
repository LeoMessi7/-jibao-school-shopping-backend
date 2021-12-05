package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "goods")
@Data
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "image_path", length = 40)
    private String image_path;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    private int status;


}

