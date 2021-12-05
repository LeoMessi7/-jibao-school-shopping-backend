package com.t09.jibao.domain;

import com.t09.jibao.service.ChatService;
import com.t09.jibao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class ChatTest {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Test
    void testInsert(){
        Chat chat = new Chat();
        ChatPK chatPK = new ChatPK();
        User user1 = userService.findById(1L);
        User user2 = userService.findById(2L);
        System.out.println(user2);
        chatPK.setBuyer(user1);
        chatPK.setReceiver(user2);
        chatPK.setComment_time(new Date());
        chat.setPk(chatPK);
        chatService.save(chat);
    }

}
