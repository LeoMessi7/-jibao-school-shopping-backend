package com.t09.jibao.utils;

import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.User;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChatUtil {

    /**
     * fill sellers information into json
     * @param chatList chat info list
     *                    contains comments and seller
     * @return map
     */
    public static List<Map<String, String>> fillChat(List<Chat> chatList){
        List<Map<String, String>> chatInfoList = new ArrayList<>();
        for(Chat chat: chatList){
            Map<String, String> chatInfo = new HashMap<>();

        }
        return chatInfoList;
    }
}
