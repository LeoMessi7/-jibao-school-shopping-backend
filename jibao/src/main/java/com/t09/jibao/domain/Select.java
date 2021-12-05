package com.t09.jibao.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "select")
@Data
public class Select implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    SelectPK pk;

    @CreatedDate
    @Column(name = "select_time")
    private Date select_time;


}

