package com.t09.jibao.dao;

import com.t09.jibao.domain.Selection;
import com.t09.jibao.domain.SelectionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectionDAO extends JpaRepository<Selection, SelectionPK> {

    @Query(name = "findSelectionByUid", nativeQuery = true,
            value = "select * from selection where uid=:uid")
    List<Selection> findSelectionByUid(@Param("uid") Long uid);
}
