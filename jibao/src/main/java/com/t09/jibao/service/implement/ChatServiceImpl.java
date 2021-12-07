package com.t09.jibao.service.implement;

import com.t09.jibao.dao.ChatDAO;
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
    private UserService userService;

    @Override
    public Chat save(Chat chat) {
        return chatDAO.save(chat);
    }

    @Override
    public Chat createChat(Long from_id, Long to_id, String content) {
        Chat chat = new Chat();
        User from = userService.findById(from_id);
        User to = userService.findById(to_id);
        ChatPK chatPK = new ChatPK();
        chatPK.setReceiver(to);
        chatPK.setSender(from);
        chatPK.setChatTime(new Date());
        chat.setPk(chatPK);
        chat.setContent(content);
        return save(chat);
    }
}