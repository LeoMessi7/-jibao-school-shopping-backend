package com.t09.jibao.dao;

import com.t09.jibao.domain.ChatPK;
import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.Dealt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealtDAO extends JpaRepository<Dealt, Long> {
}
