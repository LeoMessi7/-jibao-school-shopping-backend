package com.t09.jibao.service;

import com.t09.jibao.domain.Chat;

public interface ChatService {
    Chat save(Chat chat);

    Chat add(String from_username, String to_username, String content);
}
