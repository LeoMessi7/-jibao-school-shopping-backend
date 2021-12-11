package com.t09.jibao.domain;


import lombok.Data;
import javax.persistence.*;





@Entity(name = "category")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "subCategory")})
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", length = 20)
    private String category;

    @Column(name = "subCategory", length = 20)
    private String subCategory;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "totalSold")
    private int totalSold;

}

