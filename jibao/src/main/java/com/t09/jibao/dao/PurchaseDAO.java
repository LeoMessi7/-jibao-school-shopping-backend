package com.t09.jibao.dao;

import com.t09.jibao.Vo.GoodsVo;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Purchase;
import com.t09.jibao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDAO extends JpaRepository<Purchase, Long> {
    List<Purchase> findPurchaseByUser(User user);

    List<GoodsVo> findPurchaseAndGoodsByUid(Long uid);
}
