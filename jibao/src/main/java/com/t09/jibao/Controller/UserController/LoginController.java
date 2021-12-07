package com.t09.jibao.Controller.UserController;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.CaptchaService;
import com.t09.jibao.service.MailService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login/checkAccount")
    public String loginCheck(@RequestParam Map<String,String> params){
        String password = params.get("password");
        String email = params.get("email");
        JSONObject response = new JSONObject();
        // find by email
        User user = userService.findByEmail(email);
        // haven't been registered or active
        if(user == null || !user.isActive()){
            // user not exists
            response.put("code", 1);
        }
        else{
            // login successfully
            if(user.getPassword().equals(password)) {
                response.put("code", 0);
                response.put("avatar_url", user.getAvatarPath());
                request.getSession().setAttribute("uid", user.getId());
            }
            else
                response.put("code", 1);
        }
        return response.toJSONString();
    }
}
