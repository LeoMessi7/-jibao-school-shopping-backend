package com.t09.jibao.domain;

import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class CommentPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "seller_id")
    User buyer;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    User receiver;
}
