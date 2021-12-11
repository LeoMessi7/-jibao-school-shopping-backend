package com.t09.jibao.utils;

import com.t09.jibao.domain.Category;
import com.t09.jibao.domain.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryUtil {

    /**
     * fill sellers information into json
     * @param categoryList category info list
     * @return map
     */
    public static List<Map<String, String>> fillCategory(Map<String, List<String>> categoryList){
        List<Map<String, String>> chatInfoList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : categoryList.entrySet()) {
            Map<String, String> categoryInfo = new HashMap<>();
            categoryInfo.put("category", entry.getKey());
            categoryInfo.put("sub_category", entry.getValue().toString());
            chatInfoList.add(categoryInfo);
        }
        return chatInfoList;
    }
}
