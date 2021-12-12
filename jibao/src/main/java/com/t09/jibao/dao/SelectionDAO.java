package com.t09.jibao.dao;

import com.t09.jibao.Vo.SelectionVo;
import com.t09.jibao.domain.Selection;
import com.t09.jibao.domain.SelectionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectionDAO extends JpaRepository<Selection, SelectionPK> {

    @Query(value = "select new com.t09.jibao.Vo.SelectionVo(g, s) from selection s left join goods g on s.pk.goods.id = g.id where s.pk.user.id=:uid")
    List<SelectionVo> findSelectionByUid(@Param("uid") Long uid);
}
