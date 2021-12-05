package com.t09.jibao.dao;

import com.t09.jibao.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDAO extends JpaRepository<Goods, Long> {

}
