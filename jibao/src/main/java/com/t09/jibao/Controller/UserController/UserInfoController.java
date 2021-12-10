package com.t09.jibao.Controller.UserController;



import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.service.PurchaseService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        request.getSession();
        JSONObject response = new JSONObject();
        String avatar_path = userService.getAvatarPath(uid);
        System.out.println(avatar_path);
        response.put("avatar_url", avatar_path);
        return response.toJSONString();
    }


    /**
     * buy goods
     * @param params request params
     *               contains:
     *                  gid: goods id
     * @return response
     */
    @PostMapping("user/info/purchase")
    public String purchase(@RequestParam Map<String,String> params){
        Long uid = (long) request.getSession().getAttribute("uid");
        Long gid = (long) Integer.parseInt(params.get("gid"));
        JSONObject response = new JSONObject();
        int code = purchaseService.purchase(uid, gid);
        response.put("code", code);
        return response.toJSONString();
    }

    /**
     * update user avatar
     * @param avatar image
     * @return response
     */
    @PostMapping("user/info/updateAvatar")
    public String updateAvatar(@RequestParam(value = "avatar") MultipartFile avatar) throws IOException {
        Long uid = (long) request.getSession().getAttribute("uid");
        JSONObject response = new JSONObject();
        response.put("code", userService.updateAvatar(uid, avatar));
        return response.toJSONString();
    }

}
