package com.t09.jibao.Vo;

import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Selection;
import lombok.Data;

@Data
public class SelectionVo {
    private Goods goods;
    private Selection selection;
    public SelectionVo(Goods goods, Selection selection){
        this.goods = goods;
        this.selection = selection;
    }
}
