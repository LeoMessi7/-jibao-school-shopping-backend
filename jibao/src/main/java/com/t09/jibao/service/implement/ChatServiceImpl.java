package com.t09.jibao.service.implement;

import com.t09.jibao.dao.ChatDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.ChatPK;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.ChatService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDAO chatDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public Chat save(Chat chat) {
        return chatDAO.save(chat);
    }

    @Override
    public Chat add(String from_username, String to_username, String content) {
        Chat chat = new Chat();
        User from_user = userDAO.findFirstByName(from_username);
        User to_user = userDAO.findFirstByName(to_username);
        ChatPK chatPK = new ChatPK();
        chatPK.setReceiver(to_user);
        chatPK.setSender(from_user);
        chatPK.setChatTime(new Date());
        chat.setPk(chatPK);
        chat.setContent(content);
        return save(chat);
    }
}