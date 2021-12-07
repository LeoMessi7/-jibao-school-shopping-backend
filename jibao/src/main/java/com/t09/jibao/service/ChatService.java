package com.t09.jibao.service;

import com.t09.jibao.domain.Chat;

public interface ChatService {
    Chat save(Chat chat);

    Chat createChat(Long from_id, Long to_id, String content);
}
