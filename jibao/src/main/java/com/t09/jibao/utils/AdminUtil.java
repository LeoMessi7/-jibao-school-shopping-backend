package com.t09.jibao.utils;

import com.t09.jibao.Vo.FeedbackVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUtil {
    public static List<Map<String, String>> fillFeedback(List<FeedbackVo> feedbackVoList){
        List<Map<String, String>> feedbackInfoList = new ArrayList<>();
        for(FeedbackVo feedbackVo: feedbackVoList){
            Map<String, String> feedbackInfo = new HashMap<>();
            feedbackInfo.put("user_name", feedbackVo.getUser().getName());
            feedbackInfo.put("content", feedbackVo.getFeedback().getContent());
            feedbackInfo.put("time", feedbackVo.getFeedback().getCreate_time().toString());
            feedbackInfo.put("fid", feedbackVo.getFeedback().getId().toString());
            feedbackInfoList.add(feedbackInfo);
        }
        return feedbackInfoList;
    }
}
