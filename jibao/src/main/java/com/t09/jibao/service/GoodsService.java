package com.t09.jibao.service;

import com.t09.jibao.domain.Goods;

public interface GoodsService {

    Goods save(Goods goods);

    Goods findById(Long id);


}
