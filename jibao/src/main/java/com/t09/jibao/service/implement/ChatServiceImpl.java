package com.t09.jibao.service.implement;

import com.t09.jibao.dao.AdministratorDAO;
import com.t09.jibao.dao.ChatDAO;
import com.t09.jibao.domain.Administrator;
import com.t09.jibao.domain.Chat;
import com.t09.jibao.service.AdministratorService;
import com.t09.jibao.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDAO chatDAO;

    @Override
    public Chat save(Chat chat) {
        return chatDAO.save(chat);
    }
}