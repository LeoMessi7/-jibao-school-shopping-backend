package com.t09.jibao.Controller.AdminController;


import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.domain.Administrator;
import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.AdministratorService;
import com.t09.jibao.service.CaptchaService;
import com.t09.jibao.service.MailService;
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
public class AdminLoginController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private HttpServletRequest request;
    @Value("${expiredTime}")
    private int expiredTime;



    /**
     * feedback
     * @param params request params
     *               contains:
     *                  content: the content of feedback
     * @return response
     */
    @PostMapping("/administrator/login")
    public String adminLogin(@RequestParam Map<String, String> params) {
        String password = params.get("password");
        String email = params.get("email");
        String captcha_code = params.get("captcha_code");
        System.out.println(captcha_code);
        JSONObject response = new JSONObject();
        // find by email
        Administrator administrator = administratorService.findByEmail(email);
        if (administrator == null) {
            // administrator does not exist
            response.put("code", 1);
        } else {
            // email matches password
            if (administrator.getPassword().equals(password)) {
                response.put("code", 0);
                response.put("user_name", administrator.getName());
                request.getSession().setAttribute("aid", administrator.getId());
            } else
                response.put("code", 2);
        }
        return response.toJSONString();
    }
}
