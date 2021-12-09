package com.t09.jibao.utils;

import com.t09.jibao.domain.Goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsUtil {
    /**
     * fill goods information into json
     * @param goodsList goods list
     * @return map
     */
    public static List<Map<String, String>> fillGoods(List<Goods> goodsList){
        List<Map<String, String>> goodsInfoList = new ArrayList<>();
        // traverse
        for(Goods goods: goodsList){
            Map<String, String> goodsInfo = new HashMap<>();
            goodsInfo.put("goods_id", goods.getId().toString());
            goodsInfo.put("description", goods.getDescription());
            goodsInfo.put("category_id", goods.getCategory().getId().toString());
            goodsInfo.put("category", goods.getCategory().getCategory());
            goodsInfo.put("sub_category", goods.getCategory().getSubCategory());
            goodsInfo.put("name", goods.getName());
            // the status of goods
            String goods_status;
            if(goods.getStatus() == 0)
                goods_status = "售卖中";
            else if(goods.getStatus() == 1)
                goods_status = "已售出";
            else
                goods_status = "已下架";
            goodsInfo.put("status", goods_status);
            goodsInfoList.add(goodsInfo);
        }
        return goodsInfoList;
    }
}
