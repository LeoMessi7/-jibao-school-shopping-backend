package com.t09.jibao.Controller.UserController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.Vo.ChatUser;
import com.t09.jibao.service.ChatService;
import com.t09.jibao.service.UserService;
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

    @Autowired
    private UserService userService;

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


    /**
     * get two chat messages
     * @param params request params:
     *                  contains:
     *                      seller_name: seller name
     * @return response
     */
    @PostMapping("/get/chat")
    public String getChat(@RequestParam Map<String,String> params){
        Long uid = (long) request.getSession().getAttribute("uid");
        String seller_name = params.get("seller_name");
        List<ChatUser> chatUserList = chatService.findByBoth(uid, seller_name);
        JSONObject response = new JSONObject();
        List<Map<String, String>> chatUserInfoList = new ArrayList<>();
        for(ChatUser chatUser: chatUserList){
            Map<String, String> chatUserInfo = new HashMap<>();
            chatUserInfo.put("from_username", chatUser.getSender_name());
            chatUserInfo.put("to_username", chatUser.getReceiver_name());
            chatUserInfo.put("content", chatUser.getContent());
            chatUserInfo.put("date", chatUser.getChatTime().toString());
            chatUserInfoList.add(chatUserInfo);
        }
        response.put("chat", chatUserInfoList);
        response.put("length", chatUserInfoList.size());
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
     * @param message message
     *                  contains: content, from_username, to_username, avatar_url
     */
    @OnMessage
    public void onMessage(String message) {
        try {
            JSONObject jsonObject = JSON.parseObject(message);
            System.out.println(jsonObject);
            // parse
            String content = jsonObject.getString("content");
            String from_username = jsonObject.getString("from_username");
            String to_username = jsonObject.getString("to_username");
            String avatar_url = jsonObject.getString("avatar_url");
            System.out.println(from_username + "===>" + to_username + ":" + content);
            Map<String, Object> mapPoint = new HashMap<>();
            mapPoint.put("content", content);
            mapPoint.put("from_username", from_username);
            mapPoint.put("avatar_url", avatar_url);
            // to all
            if(Objects.equals(to_username, "all")){
                Map<String, Object> mapAll = new HashMap<>();
                mapAll.put("content", content);
                mapAll.put("from_username", "all");
                sendMessageAll(JSON.toJSONString(mapAll), from_username);
            }
            // point to point
            if (clients.get(to_username) != null) {
                mapPoint.put("to_username", to_username);
                sendMessageTo(JSON.toJSONString(mapPoint), to_username);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    /**
     * save message
     * @param params request params:
     *                  contains:
     *                      to_username: target user name
     *                      from_username: source user name
     *                      content: message
     */
    @PostMapping("/add/chat")
    public void saveChat(@RequestParam Map<String,String> params){
        String to_username = params.get("to_username");
        if(Objects.equals(to_username, "all"))
            return;
        String from_username = params.get("from_username");
        String content = params.get("content");
        chatService.add(from_username, to_username, content);
    }

    /**
     * point to point
     * @param message message
     * @param to_username target user
     */
    public void sendMessageTo(String message, String to_username) throws IOException {
        ChatController chatController = clients.get(to_username);
        chatController.session.getBasicRemote().sendText(message);
    }

    /**
     * chatting room
     * @param message message
     * @param from_user source user
     */
    public void sendMessageAll(String message, String from_user){
        for (ChatController item : clients.values()) {
            if(!Objects.equals(item.username, from_user))
            item.session.getAsyncRemote().sendText(message);
        }
    }


}

