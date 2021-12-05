package com.t09.jibao.domain;


import lombok.Data;
import javax.persistence.*;





@Entity(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", length = 20)
    private String category;

    @Column(name = "sub_category", length = 20)
    private String sub_category;

    @Column(name = "image_path", length = 40)
    private String image_path;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "total_sold")
    private int total_sold;

}

