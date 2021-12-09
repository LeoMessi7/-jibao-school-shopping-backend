package com.t09.jibao.Controller.GoodsController;


import com.alibaba.fastjson.JSONObject;
import com.t09.jibao.domain.Category;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.service.*;
import com.t09.jibao.utils.GoodsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsInfoController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private HttpServletRequest request;


    /**
     * add a new category
     * @param params request params
     *                  contains:
     *                      category & sub_category: the category of goods
     *                      description: the description of goods
     * @return response
     */
    @PostMapping("goods/info/addCategory")
    public String addCategory(@RequestParam Map<String,String> params){
        String category = params.get("category");
        String sub_category = params.get("sub_category");
        String description = params.get("description");
        Category cate = categoryService.add(category, sub_category, description);
        JSONObject response = new JSONObject();
        // it has been created
        if(cate == null)
            response.put("code", 1);
        else
            response.put("code", 0);
        return response.toJSONString();
    }


    /**
     * search the information of goods
     * @param params request params
     *               contains:
     *                  key_word: the key word user searched
     * @return the information of goods
     */
    @PostMapping("goods/search")
    public String search(@RequestParam Map<String,String> params){
        // key word
        String key_word = params.get("key_word");
        JSONObject response = new JSONObject();
        List<Goods> goodsList = goodsService.search(key_word);
        // information
        response.put("goodsInfoList", GoodsUtil.fillGoods(goodsList));
        // the length of the list
        response.put("length", goodsList.size());
        return response.toJSONString();
    }

    /**
     * upload goods
     * @param params request params
     *               contains:
     *                  description: the description of goods
     *                  name: the name of goods
     *                  price: the price of goods
     *                  category & sub_category: the category of goods
     *
     * @param image item picture
     * @return response
     */
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


    /**
     * get goods that user had bought
     * note that user should be logged in
     * @return response purchase list
     */
    @PostMapping("goods/getPurchase")
    public String getPurchase(){
        JSONObject response = new JSONObject();
        Object uid_object = request.getSession().getAttribute("uid");
        // user hasn't logged in
        if(uid_object == null)
            response.put("code", 1);
        else {
            Long uid = (long) request.getSession().getAttribute("uid");
            List<Goods> purchaseList = purchaseService.findGoodsByUid(uid);
            // information
            response.put("purchaseList", GoodsUtil.fillGoods(purchaseList));
            response.put("length", purchaseList.size());
            response.put("code", 0);
        }
        return response.toJSONString();
    }

    /**
     * withdraw the goods
     * @param params request params:
     *               contains:
     *                  gid: the id of goods
     * @return response
     */
    @PostMapping("goods/withdraw")
    public String withdraw(@RequestParam Map<String,String> params) {
        JSONObject response = new JSONObject();
        Object uid_object = request.getSession().getAttribute("uid");
        // user hasn't logged in
        if(uid_object == null)
            response.put("code", 1);
        else {
            Long gid = (long) Integer.parseInt(params.get("gid"));
            Long uid = (long) uid_object;
            Upload upload = uploadService.findByGid(gid);
            // upload is null:  the goods hasn't been uploaded
            // not equal: something wrong
            if (upload == null || !uid.equals(upload.getId()))
                response.put("code", 2);
            else {
                int code = withdrawService.withdrawGoods(uid, gid);
                response.put("code", code);
            }
        }
        return response.toJSONString();
    }




}
