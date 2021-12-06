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
public class RegisterController {


    @Value("${expiredTime}")
    private int expiredTime;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MailService mailService;


    @PostMapping("/register/checkAccount")
    public String registerCheck(@RequestParam Map<String,String> params){
        String name = params.get("name");
        String password = params.get("password");
        String email = params.get("email");
        String captcha_input = params.get("captcha_input");
        JSONObject response = new JSONObject();
        // find by email
        User user = userService.findByEmail(email);
        // haven't been registered or active
        if(user == null || !user.is_active()) {
            response.put("code", 0);
            Long image_id = (long) request.getSession().getAttribute("image_id");
            Captcha captcha = captchaService.findById(image_id);
            Date time_limit = new Date(captcha.getCreate_time().getTime() + expiredTime);
            if(captcha.getImage_captcha().equals(captcha_input) && captcha.getCreate_time().before(time_limit)) {
                User new_user = userService.create(email, name, password);
                captchaService.createEmailCaptcha(new_user, captcha, Utils.generateEmailCaptcha());
                response.put("uid", new_user.getId());
                mailService.sendCaptchaMail( "zengle", user.getEmail(), "hello", captcha.getEmail_captcha());
            }
            else {
                response.put("code", 1);
            }
        }
        else {
            response.put("code", 2);
        }
        return response.toJSONString();
    }


    @GetMapping("/register/getImageCaptcha")
    public void getImageCaptcha(HttpServletResponse response) throws IOException {
        // create captcha test
        String capText = defaultKaptcha.createText();
        if(request.getSession().getAttribute("image_id") != null) {
            Long image_id = (long) request.getSession().getAttribute("image_id");
            captchaService.deleteCaptcha(image_id);
        }
        // create captcha image
        BufferedImage image = defaultKaptcha.createImage(capText);
        Captcha captcha = captchaService.createImageCaptcha(capText);
        // create captcha
        request.getSession().setAttribute("image_id", captcha.getId());
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }


    @PostMapping("/register/checkEmailCaptcha")
    public String checkEmailCaptcha(@RequestParam Map<String,String> params) throws IOException {
        String uid_str = params.get("uid");
        String captcha_input = params.get("captcha_input");
        JSONObject response = new JSONObject();
        Long uid = (long) Integer.parseInt(uid_str);
        // find by email
        Captcha captcha = captchaService.findByUid(uid);
        Date time_limit = new Date(captcha.getCreate_time().getTime() + expiredTime);
        if(captcha.getCreate_time().after(time_limit)){
            response.put("code", 1);
        }
        else if(captcha.getEmail_captcha().equals(captcha_input)){
            response.put("code", 0);
            userService.activate(uid);
            response.put("uid", uid);
        }
        else{
            response.put("code", 2);
        }
        return response.toJSONString();
    }

}
