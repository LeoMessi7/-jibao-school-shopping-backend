package com.t09.jibao.service;

import com.t09.jibao.Vo.GoodsVo;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Purchase;
import com.t09.jibao.domain.Selection;

import java.util.List;

public interface PurchaseService {
    // save
    Purchase save(Purchase purchase);

    // find purchase object by id
    Purchase findById(Long id);

    // find goods from purchase table by uid
    List<Goods> findGoodsByUid(Long uid);

    // find goodsVo from purchase table by uid
    List<GoodsVo> findGoodsVoByUid(Long uid);

    // purchase goods
    int purchase(Long uid, Long gid);

    // purchase all
    int purchaseAll(Long uid, int total, List<Long> gid_list);
}
