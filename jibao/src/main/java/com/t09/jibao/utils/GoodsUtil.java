package com.t09.jibao.utils;

import com.t09.jibao.Vo.GoodsVo;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.UploadService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            goodsInfo.put("goods_url", goods.getImagePath());
            goodsInfo.put("price", Integer.toString(goods.getPrice()));
            // Upload upload = uploadService
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


    /**
     * fill sellers information into json
     * @param sellersInfo seller info list
     *                    contains comments and seller
     * @return map
     */
    public static List<Map<String, String>> fillSeller(Pair<List<User>, List<List<Comment>>> sellersInfo){
        List<Map<String, String>> sellersInfoList = new ArrayList<>();
        List<User> sellers = sellersInfo.getKey();
        List<List<Comment>> comments = sellersInfo.getValue();
        // traverse
        for(int i=0; i<sellers.size(); i++){
            Map<String, String> sellerInfo = new HashMap<>();
            sellerInfo.put("name", sellers.get(i).getName());
            sellerInfo.put("create_time", sellers.get(i).getCreateTime().toString());
            sellerInfo.put("email", sellers.get(i).getEmail().toString());
            sellerInfo.put("avatar_url", sellers.get(i).getAvatarPath());
            List<Comment> comment = comments.get(i);
            sellerInfo.put("comments_total", Integer.toString(comment.size()));
            sellerInfo.put("comments", comment.stream().map(Comment::getContent).collect(Collectors.toList()).toString());
            double mark = 0;
            for (Comment value : comment) {
                mark += value.getMark();
            }
            if(mark == 0)
                mark = 5;
            else
                mark /= comment.size();
            sellerInfo.put("mark", Double.toString(mark));
            sellersInfoList.add(sellerInfo);
        }
        return sellersInfoList;
    }




    /**
     * fill goods and buyer information into json
     * @param goodsVoList goods list
     * @return map
     */
    public static List<Map<String, String>> fillGoodsAndBuyer(List<GoodsVo> goodsVoList){
        List<Map<String, String>> goodsVoInfoList = new ArrayList<>();
        // traverse
        for(GoodsVo goodsVo: goodsVoList){
            Map<String, String> goodsVoInfo = new HashMap<>();
            goodsVoInfo.put("goods_id", goodsVo.getGoods().getId().toString());
            goodsVoInfo.put("description", goodsVo.getGoods().getDescription());
            goodsVoInfo.put("category_id", goodsVo.getGoods().getCategory().getId().toString());
            goodsVoInfo.put("category", goodsVo.getGoods().getCategory().getCategory());
            goodsVoInfo.put("sub_category", goodsVo.getGoods().getCategory().getSubCategory());
            goodsVoInfo.put("name", goodsVo.getGoods().getName());
            goodsVoInfo.put("goods_url", goodsVo.getGoods().getImagePath());
            goodsVoInfo.put("price", Integer.toString(goodsVo.getGoods().getPrice()));
            // the status of goods
            String goods_status;
            if(goodsVo.getGoods().getStatus() == 0)
                goods_status = "售卖中";
            else if(goodsVo.getGoods().getStatus() == 1)
                goods_status = "已售出";
            else
                goods_status = "已下架";
            goodsVoInfo.put("status", goods_status);
            if(goods_status.equals("已售出")){
                goodsVoInfo.put("buy_date", goodsVo.getPurchase().getPurchase_time().toString());
                goodsVoInfo.put("buyer_name", goodsVo.getBuyer().getName());
            }
            goodsVoInfoList.add(goodsVoInfo);
        }
        return goodsVoInfoList;
    }


}
