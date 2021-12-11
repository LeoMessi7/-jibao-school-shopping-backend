package com.t09.jibao.Vo;
import lombok.Data;

import java.util.Date;

@Data
public class ChatUser implements Comparable<ChatUser>{
    private String receiver_name;
    private String sender_name;
    private String content;
    private Date chatTime;
    public ChatUser(String receiver_name, String sender_name, String content, Date chatTime){
        this.receiver_name = receiver_name;
        this.sender_name = sender_name;
        this.content = content;
        this.chatTime = chatTime;
    }

    @Override
    public int compareTo(ChatUser chatUser) {
        if(this.getChatTime().before(chatUser.getChatTime()))
            return -1;
        return 1;
    }
}
