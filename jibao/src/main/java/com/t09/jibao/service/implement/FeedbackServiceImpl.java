package com.t09.jibao.service.implement;

import com.t09.jibao.dao.FeedbackDAO;
import com.t09.jibao.dao.GoodsDAO;
import com.t09.jibao.domain.Feedback;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.service.FeedbackService;
import com.t09.jibao.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Override
    public Feedback save(Feedback feedback) {
        return feedbackDAO.save(feedback);
    }

    @Override
    public Feedback findById(Long id) {
        return feedbackDAO.findById(id).get();
    }

}