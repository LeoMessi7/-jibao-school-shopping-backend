package com.t09.jibao.dao;

import com.t09.jibao.Vo.FeedbackVo;
import com.t09.jibao.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackDAO extends JpaRepository<Feedback, Long> {
    @Query(value = "select new com.t09.jibao.Vo.FeedbackVo(u, f) from feedback f left join user u on f.user.id = u.id")
    List<FeedbackVo> findAllFeedbackVo();
}
