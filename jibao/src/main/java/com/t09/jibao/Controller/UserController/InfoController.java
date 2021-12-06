package com.t09.jibao.Controller.UserController;



import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.file.FileUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.t09.jibao.Controller.Utils;
import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.CaptchaService;
import com.t09.jibao.service.MailService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
public class InfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MailService mailService;

/*
    @PostMapping("/info/updateAvatar")
    public String registerCheck(@RequestParam Map<String,String> params){
        return "123";
    }

    @PostMapping("/upload/avatar")
    public String getAvatar(@RequestParam Map<String,String> params){
        String uid_str = params.get("uid");
        JSONObject response = new JSONObject();
        Long uid = (long) Integer.parseInt(uid_str);
        String avatar_path = userService.getAvatarPath(uid);
        response.put("avatar_url", avatar_path);
        return response.toJSONString();
    }

 */

}
