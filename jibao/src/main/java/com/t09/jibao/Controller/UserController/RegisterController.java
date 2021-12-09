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


    /**
     * cheek input when registering
     * @param params request params
     *               contains:
     *                  name: user name
     *                  password: user password
     *                  email: user email
     *                  captcha_input: image captcha
     * @return response
     */
    @PostMapping("/register/checkAccount")
    public String registerCheck(@RequestParam Map<String,String> params){
        System.out.println(request.getSession().getAttribute("image_id"));
        String name = params.get("name");
        String password = params.get("password");
        String email = params.get("email");
        String captcha_input = params.get("captcha_input");
        JSONObject response = new JSONObject();
        // find by email
        User user = userService.findByEmail(email);
        // hasn't been registered or active
        if(user == null || !user.isActive()) {
            response.put("code", 0);
            Long image_id = (long) request.getSession().getAttribute("image_id");
            Captcha captcha = captchaService.findById(image_id);
            Date time_limit = new Date(captcha.getCreate_time().getTime() + expiredTime);
            // captcha input should be correct and before ddl
            if(captcha.getImage_captcha().equals(captcha_input) && captcha.getCreate_time().before(time_limit)) {
                User new_user = userService.create(email, name, password);
                captchaService.createEmailCaptcha(new_user, captcha, Utils.generateEmailCaptcha());
                response.put("uid", new_user.getId());
                mailService.sendCaptchaMail( "zengle", new_user.getEmail(), "hello", captcha.getEmail_captcha());
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


    /**
     * get image captcha
     * @param response image
     */
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

        System.out.println(request.getSession().getAttribute("image_id"));

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", response.getOutputStream());
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
