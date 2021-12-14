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


    /**
     * buy goods
     * @param params request params
     *               contains:
     *                  gid: goods id
     * @return response
     */
    @PostMapping("/user/info/purchase")
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
    @PostMapping("/user/info/updateAvatar")
    public String updateAvatar(@RequestParam(value = "avatar") MultipartFile avatar) throws IOException{
        Long uid = (long) request.getSession().getAttribute("uid");
        JSONObject response = new JSONObject();
        String url = userService.updateAvatar(uid, avatar);
        if(url.equals(""))
            response.put("code", 1);
        else{
            response.put("code", 0);
            response.put("avatar_url", url);
        }
        return response.toJSONString();
    }

    /**
     * change password
     * @param params request params
     *               contains:
     *                  password: password
     * @return response
     */
    @PostMapping("/user/info/changePassword")
    public String changePassword(@RequestParam Map<String, String> params){
        Long uid = (long) request.getSession().getAttribute("uid");
        String password = params.get("password");
        JSONObject response = new JSONObject();
        response.put("code", userService.changePassword(uid, password));
        return response.toJSONString();
    }

    @PostMapping("/user/info/update")
    public String userInfoUpdate(@RequestParam Map<String, String> params){
        Long uid = (long) request.getSession().getAttribute("uid");
        String major = params.get("major");
        String campus = params.get("campus");
        userService.updateInfo(uid, major, campus);
        JSONObject response = new JSONObject();
        response.put("code", 0);
        return response.toJSONString();
    }

    @PostMapping("/user/info/getBalance")
    public int getBalance(){
        Long uid = (long) request.getSession().getAttribute("uid");
        return userService.findById(uid).getBalance();
    }

}
