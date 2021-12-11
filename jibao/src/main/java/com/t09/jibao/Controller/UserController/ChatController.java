package com.t09.jibao.Controller.UserController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.Vo.ChatUser;
import com.t09.jibao.domain.Chat;
import com.t09.jibao.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@ServerEndpoint("/chat/{username}")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // online number
    public static volatile AtomicInteger onlineNumber = new AtomicInteger(0);

    //clients: keep username as key
    private static Map<String, ChatController> clients = new ConcurrentHashMap<>();

    // request
    @Autowired
    private HttpServletRequest request;

    // session
    private Session session;

    // username
    private String username;


    /**
     * second设置为静态的 公用一个消息
     * map ConcurrentMap为线程安全的map  HashMap不安全
     */
    private static ConcurrentMap<String, Map<String, List<Object>>> messageMap = new ConcurrentHashMap<>();




    @PostMapping("/get/chat")
    public String comment(@RequestParam Map<String,String> params){
        Long uid = 1L;//(long) request.getSession().getAttribute("uid");
        String seller_name = params.get("seller_name");
        List<ChatUser> chatUserList = chatService.findByBoth(uid, seller_name);
        JSONObject response = new JSONObject();
        for(ChatUser chatUser: chatUserList){

        }
        return response.toJSONString();
    }



    /**
     * connect
     * @param session session
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        onlineNumber.addAndGet(1);
        this.username = username;
        this.session = session;
        System.out.println("online number:" + onlineNumber);
        // create a new client
        clients.put(username, this);
    }

    /**
     * report error
     * @param session session
     * @param error error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("error" + error.getMessage());
    }

    /**
     * close connect
     */
    @OnClose
    public void onClose() {
        onlineNumber.addAndGet(-1);
        System.out.println("online number:" + onlineNumber);
        clients.remove(username);
    }

    /**
     * receive message from server
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            JSONObject jsonObject = JSON.parseObject(message);
            String content = jsonObject.getString("content");
            String from_username = jsonObject.getString("from_username");
            String to_username = jsonObject.getString("to_username");
            System.out.println(from_username + "===>" + to_username + ":" + content);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("content", content);
            map1.put("from_username", from_username);
            if (clients.get(to_username) != null) {
                map1.put("to_username", to_username);
                sendMessageTo(JSON.toJSONString(map1), to_username);
            } else if (clients.get(to_username) == null) {
                chatService.add(from_username, to_username, content);
            }
        } catch (Exception e) {
            System.out.println("发生了错误了");
        }
    }

    public void sendMessageTo(String message, String to_username) throws IOException {
        ChatController chatController = clients.get(to_username);
        chatController.session.getBasicRemote().sendText(message);
    }

    public void sendMessageAll(String message, String FromUserName) throws IOException {
        for (ChatController item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized AtomicInteger getOnlineCount() {
        return onlineNumber;
    }

    public void saveMessage(String tousername, String fromusername, String message) {
        if (messageMap.get(tousername) == null) {
            //firs 用户不在线时 第一次给他发消息
            Map<String, List<Object>> maps = new HashMap<>();//该用户的所有消息
            List<Object> list = new ArrayList<>();//该用户发的离线消息的集合
            list.add(message);
            maps.put(fromusername, list);
            messageMap.put(tousername, maps);
        } else {
            //first 不在线再次发送消息
            //second 给用户的所有消息
            Map<String, List<Object>> listObject = messageMap.get(tousername);
            List<Object> objects = new ArrayList<>();
            if (listObject.get(fromusername) != null) {//first 这个用户给收消息的这个用户发过消息
                //second此用户给该用户发送过离线消息（此用户给该用户发过的所有消息）
                objects = listObject.get(fromusername);
                objects.add(message);//加上这次发送的消息
                //maps.put(sendUserId, objects);
                //替换原来的map
                listObject.put(fromusername, objects);
            } else {//third 这个用户没给该用户发送过离线消息
                objects.add(message);
                listObject.put(fromusername, objects);
            }
        }
    }
}

