package com.t09.jibao.dao;

import com.t09.jibao.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO extends JpaRepository<Comment, CommentPK> {
    @Query(name = "findCommentBySid", nativeQuery = true,
            value = "select * from comment where seller_id=:sid")
    List<Comment> findCommentBySid(@Param("sid") Long sid);
}
