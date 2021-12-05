package com.t09.jibao.domain;


import lombok.Data;
import javax.persistence.*;

@Entity(name = "goods")
@Data
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cid")
    Category category;

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

