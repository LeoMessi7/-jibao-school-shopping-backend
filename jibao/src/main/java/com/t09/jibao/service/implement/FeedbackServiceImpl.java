package com.t09.jibao.service.implement;

import com.t09.jibao.dao.ChatDAO;
import com.t09.jibao.dao.FeedbackDAO;
import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.Feedback;
import com.t09.jibao.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Override
    public Feedback save(Feedback feedback) {
        return feedbackDAO.save(feedback);
    }
}
