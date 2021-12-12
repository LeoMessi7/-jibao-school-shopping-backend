package com.t09.jibao.utils;

import com.t09.jibao.Vo.GoodsVo;
import com.t09.jibao.Vo.SelectionVo;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.User;
import javafx.util.Pair;

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
            goodsInfo.put("goods_name", goods.getName());
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
    public static List<Pair<Map<String, String>, Map<String, List<String>>>> fillSeller(Pair<List<User>, List<List<Comment>>> sellersInfo){
        List<Pair<Map<String, String>, Map<String, List<String>>>> sellersInfoList = new ArrayList<>();
        List<User> sellers = sellersInfo.getKey();
        List<List<Comment>> comments = sellersInfo.getValue();
        // traverse
        for(int i=0; i<sellers.size(); i++){
            Map<String, String> sellerInfo = new HashMap<>();
            Map<String, List<String>> commentsInfo = new HashMap<>();
            sellerInfo.put("name", sellers.get(i).getName());
            sellerInfo.put("create_time", sellers.get(i).getCreateTime().toString());
            sellerInfo.put("email", sellers.get(i).getEmail().toString());
            sellerInfo.put("avatar_url", sellers.get(i).getAvatarPath());
            List<Comment> comment = comments.get(i);
            sellerInfo.put("comments_total", Integer.toString(comment.size()));
            commentsInfo.put("comments", comment.stream().map(Comment::getContent).collect(Collectors.toList()));
            double mark = 0;
            for (Comment value : comment) {
                mark += value.getMark();
            }
            if(mark == 0)
                mark = 5;
            else
                mark /= comment.size();
            sellerInfo.put("mark", Double.toString(mark));
            sellersInfoList.add(new Pair<>(sellerInfo, commentsInfo));
        }
        return sellersInfoList;
    }




    /**
     * fill goods and buyer information into json
     * @param goodsVoList goods list
     * @return map
     */
    public static List<Map<String, String>> fillGoodsAndUser(List<GoodsVo> goodsVoList){
        List<Map<String, String>> goodsVoInfoList = new ArrayList<>();
        // traverse
        for(GoodsVo goodsVo: goodsVoList){
            Map<String, String> goodsVoInfo = new HashMap<>();
            goodsVoInfo.put("goods_id", goodsVo.getGoods().getId().toString());
            goodsVoInfo.put("description", goodsVo.getGoods().getDescription());
            goodsVoInfo.put("category_id", goodsVo.getGoods().getCategory().getId().toString());
            goodsVoInfo.put("category", goodsVo.getGoods().getCategory().getCategory());
            goodsVoInfo.put("sub_category", goodsVo.getGoods().getCategory().getSubCategory());
            goodsVoInfo.put("goods_name", goodsVo.getGoods().getName());
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
                goodsVoInfo.put("date", goodsVo.getPurchase().getPurchase_time().toString());
                goodsVoInfo.put("user_name", goodsVo.getUser().getName());
                goodsVoInfo.put("avatar_url", goodsVo.getUser().getAvatarPath());
            }
            System.out.println(goodsVoInfo);
            goodsVoInfoList.add(goodsVoInfo);
        }
        return goodsVoInfoList;
    }


    /**
     * fill selection information into json
     * @param selectionVoList selection list
     * @return map
     */
    public static List<Map<String, String>> fillSelection(List<SelectionVo> selectionVoList){
        List<Map<String, String>> selectionInfoList = new ArrayList<>();
        // traverse
        for(SelectionVo selectionVo: selectionVoList){
            Map<String, String> selectionInfo = new HashMap<>();
            selectionInfo.put("goods_id", selectionVo.getGoods().getId().toString());
            selectionInfo.put("description", selectionVo.getGoods().getDescription());
            selectionInfo.put("category_id", selectionVo.getGoods().getCategory().getId().toString());
            selectionInfo.put("category", selectionVo.getGoods().getCategory().getCategory());
            selectionInfo.put("sub_category", selectionVo.getGoods().getCategory().getSubCategory());
            selectionInfo.put("goods_name", selectionVo.getGoods().getName());
            selectionInfo.put("goods_url", selectionVo.getGoods().getImagePath());
            selectionInfo.put("price", Integer.toString(selectionVo.getGoods().getPrice()));
            selectionInfo.put("select_time", selectionVo.getSelection().getSelect_time().toString());
            // the status of goods
            String goods_status;
            if(selectionVo.getGoods().getStatus() == 0)
                goods_status = "售卖中";
            else if(selectionVo.getGoods().getStatus() == 1)
                goods_status = "已售出";
            else
                goods_status = "已下架";
            selectionInfo.put("status", goods_status);
            selectionInfoList.add(selectionInfo);
        }
        return selectionInfoList;
    }


}



