package com.t09.jibao.domain;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;





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

    @Column(name = "total_sold", length = 40)
    private String image_path;

    @Column(name = "description", length = 512)
    private String description;

}

