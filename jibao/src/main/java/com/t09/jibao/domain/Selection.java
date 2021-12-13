package com.t09.jibao.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "select_time")
    private Date select_time;


}

