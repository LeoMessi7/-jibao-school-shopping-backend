package com.t09.jibao.Controller.GoodsController;


import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.domain.Category;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.service.CategoryService;
import com.t09.jibao.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    @PostMapping("goods/search")
    public String search(@RequestParam Map<String,String> params){
        String content = params.get("content");
        JSONObject response = new JSONObject();
        List<Goods> goodsList = goodsService.search(content);
        List<Map<String, String>> goodsInfoList = new ArrayList<>();
        for(Goods goods: goodsList){
            Map<String, String> goodsInfo = new HashMap<>();
            goodsInfo.put("goods_id", goods.getId().toString());
            goodsInfo.put("description", goods.getDescription());
            goodsInfo.put("category_id", goods.getCategory().getId().toString());
            goodsInfo.put("category", goods.getCategory().getCategory());
            goodsInfo.put("sub_category", goods.getCategory().getSubCategory());
            goodsInfo.put("name", goods.getName());
            goodsInfoList.add(goodsInfo);
        }
        response.put("goodsList", goodsInfoList);
        return response.toJSONString();
    }

    @PostMapping("goods/upload")
    public String upload(@RequestParam Map<String,String> params,
                         @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        String description = params.get("description");
        String name = params.get("name");
        String category = params.get("category");
        String sub_category = params.get("sub_category");
        String price_str = params.get("price");
        int price = Integer.parseInt(price_str);
        Goods goods = goodsService.add(category, sub_category,name, price, description, image);
        JSONObject response = new JSONObject();
        if(goods == null)
            response.put("code", 1);
        else
            response.put("code", 0);
        return response.toJSONString();
    }


}
