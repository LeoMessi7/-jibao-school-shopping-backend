package com.t09.jibao.service;

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

    // purchase goods
    int purchase(Long uid, Long gid);
}
