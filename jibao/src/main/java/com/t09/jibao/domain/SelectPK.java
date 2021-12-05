package com.t09.jibao.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class SelectPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "uid")
    User user;

    @ManyToOne
    @JoinColumn(name = "gid")
    Goods goods;
}
