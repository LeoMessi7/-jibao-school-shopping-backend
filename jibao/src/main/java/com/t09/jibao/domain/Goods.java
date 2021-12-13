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

    @Column(name = "imagePath", length = 40)
    private String imagePath;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    private int status;

    @Column(name = "campus", length=30)
    private String campus;


}

