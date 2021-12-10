package com.t09.jibao.Controller.UserController;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.t09.jibao.Controller.Utils;
import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.CaptchaService;
import com.t09.jibao.service.MailService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
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


    /**
     * cheek input when registering
     * @param params request params
     *               contains:
     *                  name: user name
     *                  password: user password
     *                  email: user email
     * @return response
     */
    @PostMapping("/register/checkAccount")
    public String registerCheck(@RequestParam Map<String,String> params){
        String name = params.get("name");
        String password = params.get("password");
        String email = params.get("email");
        JSONObject response = new JSONObject();
        // find by email
        User user = userService.findByEmail(email);
        // hasn't been registered or active
        if(user == null || !user.isActive()) {
            response.put("code", 0);
            User new_user = userService.create(email, name, password);
            Captcha captcha = captchaService.createEmailCaptcha(new_user, Utils.generateEmailCaptcha());
            response.put("uid", new_user.getId());
            mailService.sendCaptchaMail( "zengle", new_user.getEmail(), "hello", captcha.getEmail_captcha());
        }
        else {
            response.put("code", 1);
        }
        return response.toJSONString();
    }


    /**
     * check email captcha
     * @param params request params
     *               contains:
     *                  email: user email
     *                  captcha_input: captcha input
     * @return error code
     */
    @PostMapping("/register/checkEmailCaptcha")
    public String checkEmailCaptcha(@RequestParam Map<String,String> params) throws IOException {
        String email = params.get("email");
        String captcha_input = params.get("captcha_input");
        JSONObject response = new JSONObject();
        response.put("code", userService.activate(email, captcha_input));
        return response.toJSONString();
    }
}
