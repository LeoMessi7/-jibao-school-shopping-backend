package com.t09.jibao.dao;

import com.t09.jibao.domain.ChatPK;
import com.t09.jibao.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatDAO extends JpaRepository<Chat, ChatPK> {
}
