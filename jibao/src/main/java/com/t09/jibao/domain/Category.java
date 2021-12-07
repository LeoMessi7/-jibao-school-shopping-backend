package com.t09.jibao.domain;


import lombok.Data;
import javax.persistence.*;





@Entity(name = "category")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"category", "subCategory"})})
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", length = 20)
    private String category;

    @Column(name = "subCategory", length = 20)
    private String subCategory;

    @Column(name = "imagePath", length = 40)
    private String imagePath;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "totalSold")
    private int totalSold;

}

