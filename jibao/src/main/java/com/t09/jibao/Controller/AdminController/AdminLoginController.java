package com.t09.jibao.Controller.AdminController;


import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.domain.Administrator;
import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.*;
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
    private CategoryService categoryService;

    @Autowired
    private HttpServletRequest request;

    /**
     * administrator login
     * @param params request params
     *               contains:
     *                  email: administrator email
     *                  password: administrator password
     * @return response
     */
    @PostMapping("/administrator/login")
    public String adminLogin(@RequestParam Map<String, String> params) {
        String password = params.get("password");
        String email = params.get("email");
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

    /**
     * add category
     * @param params request params
     *               contains:
     *                  category & sub_category & description : category information
     * @return response
     */
    @PostMapping("/administrator/addCategory")
    public String addCategory(@RequestParam Map<String, String> params) {
        String category = params.get("category");
        String sub_category = params.get("sub_category");
        String description = params.get("description");
        int code = categoryService.add(category, sub_category, description);
        JSONObject response = new JSONObject();
        response.put("code", code);
        return response.toJSONString();
    }


    /**
     * add category
     * @param params request params
     *               contains:
     *                  sub_category
     * @return response
     */
    @PostMapping("/administrator/deleteCategory")
    public String deleteCategory(@RequestParam Map<String, String> params) {
        String sub_category = params.get("sub_category");
        categoryService.deleteCategory(sub_category);
        JSONObject response = new JSONObject();
        response.put("code", 0);
        return response.toJSONString();
    }


    /**
     * add category
     * @param params request params
     *               contains:
     *                  category & sub_category & description : category information
     * @return response
     */
    @PostMapping("/administrator/updateCategory")
    public String updateCategory(@RequestParam Map<String, String> params) {
        String category = params.get("category");
        String sub_category = params.get("sub_category");
        String description = params.get("description");
        int code = categoryService.updateCategory(category, sub_category, description);
        JSONObject response = new JSONObject();
        response.put("code", code);
        return response.toJSONString();
    }

    /**
     * get category
     * @return response
     */
    @PostMapping("/administrator/getCategory")
    public String getCategory() {
        JSONObject response = new JSONObject();
        response.put("category", categoryService.getCategory());
        return response.toJSONString();
    }



}
