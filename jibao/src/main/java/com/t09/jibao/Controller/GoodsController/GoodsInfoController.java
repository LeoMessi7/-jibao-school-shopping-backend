package com.t09.jibao.Controller.GoodsController;


import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.domain.Category;
import com.t09.jibao.service.CategoryService;
import com.t09.jibao.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GoodsInfoController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("goods/info/addCategory")
    public String addCategory(@RequestParam Map<String,String> params){
        String category = params.get("category");
        String sub_category = params.get("sub_category");
        String description = params.get("description");
        Category cate = categoryService.create(category, sub_category, description);
        JSONObject response = new JSONObject();
        if(cate == null)
            response.put("code", 1);
        else
            response.put("code", 0);
        return response.toJSONString();
    }


}
