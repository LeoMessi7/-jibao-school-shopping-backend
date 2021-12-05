package com.t09.jibao.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "selection")
@Data
public class Selection implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    SelectionPK pk;

    @CreatedDate
    @Column(name = "select_time")
    private Date select_time;


}

