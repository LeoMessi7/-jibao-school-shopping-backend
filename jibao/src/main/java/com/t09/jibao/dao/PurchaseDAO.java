package com.t09.jibao.dao;

import com.t09.jibao.Vo.GoodsVo;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Purchase;
import com.t09.jibao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDAO extends JpaRepository<Purchase, Long> {
    List<Purchase> findPurchaseByUser(User user);

    @Query(value = "select new com.t09.jibao.Vo.GoodsVo(g, p, seller) from user buyer left join purchase p on buyer.id = p.user.id left join goods g on p.goods.id = g.id left join upload u on u.goods.id = g.id left join user seller on seller.id = u.user.id where buyer.id=:uid")
    List<GoodsVo> findPurchaseAndGoodsByUid(Long uid);
}
