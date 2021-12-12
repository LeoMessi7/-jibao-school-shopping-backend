package com.t09.jibao.Vo;

import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Purchase;
import com.t09.jibao.domain.User;
import lombok.Data;

@Data
public class GoodsVo {
    private Goods goods;
    private Purchase purchase;
    private User user;
    public GoodsVo(Goods g, Purchase p, User u) {
        this.goods = g;
        this.purchase = p;
        this.user = u;
    }
}
