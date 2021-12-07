package com.t09.jibao.Controller.UserController;



import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.service.CaptchaService;
import com.t09.jibao.service.MailService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class UserInfoController {

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
*/

    @PostMapping("user/info/getAvatar")
    public String getAvatar(@RequestParam Map<String,String> params){
        String uid_str = params.get("uid");
        JSONObject response = new JSONObject();
        Long uid = (long) Integer.parseInt(uid_str);
        String avatar_path = userService.getAvatarPath(uid);
        System.out.println(avatar_path);
        response.put("avatar_url", avatar_path);
        return response.toJSONString();
    }


}
