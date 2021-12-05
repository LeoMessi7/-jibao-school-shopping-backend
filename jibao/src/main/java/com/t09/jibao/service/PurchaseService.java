package com.t09.jibao.service;

import com.t09.jibao.domain.Purchase;
import com.t09.jibao.domain.Selection;

import java.util.List;

public interface PurchaseService {
    Purchase save(Purchase purchase);

    Purchase findById(Long id);

    List<Purchase> findByUid(Long uid);
}
