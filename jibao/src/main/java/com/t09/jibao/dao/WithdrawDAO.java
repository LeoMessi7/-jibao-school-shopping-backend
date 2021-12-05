package com.t09.jibao.dao;

import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawDAO extends JpaRepository<Withdraw, Long>  {
    List<Withdraw> findWithdrawByGoods(Goods goods);
}
