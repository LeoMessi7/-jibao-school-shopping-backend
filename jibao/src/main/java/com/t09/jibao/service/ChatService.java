package com.t09.jibao.service;

import com.t09.jibao.Vo.ChatUser;
import com.t09.jibao.domain.Chat;

import java.util.List;

public interface ChatService {
    // save
    Chat save(Chat chat);

    // add message
    Chat add(String from_username, String to_username, String content);

    // find by both of them
    List<ChatUser> findByBoth(Long uid, String seller_name);
}
