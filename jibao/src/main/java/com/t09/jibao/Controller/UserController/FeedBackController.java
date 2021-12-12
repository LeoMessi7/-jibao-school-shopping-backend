package com.t09.jibao.Controller.UserController;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.t09.jibao.Controller.Utils;
import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.Feedback;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class FeedBackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MailService mailService;

    @Autowired
    private HttpServletRequest request;


    /**
     * feedback
     * @param params request params
     *               contains:
     *                  content: the content of feedback
     * @return response
     */
    @PostMapping("/feedback")
    public String feedback(@RequestParam Map<String,String> params){
        Long uid = (long) request.getSession().getAttribute("uid");
        String content = params.get("content");
        int code = feedbackService.add(uid, content);
        JSONObject response = new JSONObject();
        response.put("code", code);
        return response.toJSONString();
    }






    @PostMapping("/comment/check")
    public String commentCheck(@RequestParam Map<String,String> params){
        Long uid = (long) request.getSession().getAttribute("uid");
        String seller_name = params.get("seller_name");
        int code = commentService.check(uid, seller_name);
        JSONObject response = new JSONObject();
        response.put("code", code);
        return response.toJSONString();
    }


    @PostMapping("/comment")
    public String comment(@RequestParam Map<String,String> params){
        Long uid = (long) request.getSession().getAttribute("uid");
        String seller_name = params.get("seller_name");
        String content = params.get("content");
        double mark = Double.parseDouble(params.get("mark"));
        int code = commentService.add(uid, seller_name, content, mark);
        JSONObject response = new JSONObject();
        response.put("code", code);
        return response.toJSONString();
    }

}
