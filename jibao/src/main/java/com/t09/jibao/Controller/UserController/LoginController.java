package com.t09.jibao.Controller.UserController;

import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.CaptchaService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
public class LoginController {

    @Value("${expiredTime}")
    private int expiredTime;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CaptchaService captchaService;

    /**
     * check email and password when user login in
     * @param params request params
     *               contains:
     *                  email: user email
     *                  password: user password
     *                  code: captcha code
     * @return response
     *
     */
    @PostMapping("/login/checkAccount")
    public String loginCheck(@RequestParam Map<String,String> params){
        String password = params.get("password");
        String email = params.get("email");
        String captcha_code = params.get("captcha_code");
        System.out.println(captcha_code);
        JSONObject response = new JSONObject();
        // find by email
        User user = userService.findByEmail(email);
        // hasn't been registered or activated
        if(user == null || !user.isActive()){
            // user does not exist
            response.put("code", 1);
        }
        else{
            // email matches password
            if(user.getPassword().equals(password)) {
                Long image_id = (long) request.getSession().getAttribute("image_id");
                System.out.println(request.getRequestURL());
                Captcha captcha = captchaService.findById(image_id);
                Date time_limit = new Date(captcha.getCreate_time().getTime() + expiredTime);
                // captcha input should be correct and before ddl
                if(captcha.getImage_captcha().equals(captcha_code) && captcha.getCreate_time().before(time_limit)) {
                    response.put("code", 0);
                    response.put("avatar_url", user.getAvatarPath());
                    response.put("user_name", user.getName());
                    request.getSession().setAttribute("uid", user.getId());
                }else
                    response.put("code", 3);
            }
            else
                response.put("code", 2);
        }
        return response.toJSONString();
    }
}
