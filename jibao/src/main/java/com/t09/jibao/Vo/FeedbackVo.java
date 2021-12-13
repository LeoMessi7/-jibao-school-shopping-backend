package com.t09.jibao.Vo;

import com.t09.jibao.domain.Feedback;
import com.t09.jibao.domain.User;
import lombok.Data;

@Data
public class FeedbackVo {
    private User user;
    private Feedback feedback;
    public FeedbackVo(User user, Feedback feedback){
        this.user = user;
        this.feedback = feedback;
    }
}
