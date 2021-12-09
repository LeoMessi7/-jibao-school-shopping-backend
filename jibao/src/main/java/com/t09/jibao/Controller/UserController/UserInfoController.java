package com.t09.jibao.Controller.UserController;



import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Purchase;
import com.t09.jibao.service.CaptchaService;
import com.t09.jibao.service.MailService;
import com.t09.jibao.service.PurchaseService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserInfoController {

    @Autowired
    private UserService userService;
    @Autowired
    private PurchaseService purchaseService;


    @Autowired
    private HttpServletRequest request;


    @PostMapping("user/info/getAvatar")
    public String getAvatar(@RequestParam Map<String,String> params){
        Long uid = (long) request.getSession().getAttribute("uid");
        JSONObject response = new JSONObject();
        String avatar_path = userService.getAvatarPath(uid);
        System.out.println(avatar_path);
        response.put("avatar_url", avatar_path);
        return response.toJSONString();
    }



    @PostMapping("user/info/purchase")
    public String purchase(@RequestParam Map<String,String> params){
        Long uid = (long) request.getSession().getAttribute("uid");
        Long gid = (long) Integer.parseInt(params.get("gid"));
        JSONObject response = new JSONObject();
        int code = purchaseService.purchase(uid, gid);
        response.put("code", code);
        return response.toJSONString();
    }


}
