package com.t09.jibao.dao;

import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.ChatPK;
import com.t09.jibao.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackDAO extends JpaRepository<Feedback, Long> {
}
