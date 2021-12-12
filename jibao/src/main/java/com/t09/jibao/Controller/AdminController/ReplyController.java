package com.t09.jibao.Controller.AdminController;


import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.MailService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ReplyController {
    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     * feedback
     * @param params request params
     *               contains:
     *                  content: the content of feedback
     * @return response
     */
    @PostMapping("/administrator/feedback")
    public String feedback(@RequestParam Map<String,String> params){
        Long aid = (long) request.getSession().getAttribute("aid");
        String user_name = params.get("user_name");
        String content = params.get("content");
        User user = userService.findByName(user_name);
        mailService.sendFeedbackMail("zzl", user.getEmail(), "反馈", content);
        JSONObject response = new JSONObject();
        response.put("code", 0);
        return response.toJSONString();
    }


}
