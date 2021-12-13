package com.t09.jibao.Controller.AdminController;


import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.Vo.FeedbackVo;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.DealtService;
import com.t09.jibao.service.FeedbackService;
import com.t09.jibao.service.MailService;
import com.t09.jibao.service.UserService;
import com.t09.jibao.utils.AdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ReplyController {
    @Autowired
    private MailService mailService;

    @Autowired
    private DealtService dealtService;

    @Autowired
    private UserService userService;

    @Autowired
    private FeedbackService feedbackService;

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
        Long aid = 1L;//(long) request.getSession().getAttribute("aid");
        String user_name = params.get("user_name");
        String content = params.get("content");
        Long fid = (long) Integer.parseInt(params.get("fid"));
        User user = userService.findByName(user_name);
        mailService.sendFeedbackMail("zzl", user.getEmail(), "反馈", content);
        System.out.println(content);
        dealtService.add(user_name, aid, fid, content);
        JSONObject response = new JSONObject();
        response.put("code", 0);
        return response.toJSONString();
    }


    /**
     * get feedback
     * @return response
     */
    @PostMapping("/administrator/get/feedback")
    public String getFeedback(){
        List<FeedbackVo> feedbacks = feedbackService.findAll();
        JSONObject response = new JSONObject();
        response.put("feedback", AdminUtil.fillFeedback(feedbacks));
        return response.toJSONString();
    }



}
