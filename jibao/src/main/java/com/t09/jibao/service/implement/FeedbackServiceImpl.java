package com.t09.jibao.service.implement;

import com.t09.jibao.Vo.FeedbackVo;
import com.t09.jibao.dao.FeedbackDAO;
import com.t09.jibao.dao.GoodsDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Feedback;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.FeedbackService;
import com.t09.jibao.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * save
     * @param feedback feedback object
     * @return feedback object
     */
    @Override
    public Feedback save(Feedback feedback) {
        return feedbackDAO.save(feedback);
    }

    /**
     * add a feedback
     * @param uid user id
     * @param content the content of feedback
     * @return error code
     */
    @Override
    public int add(Long uid, String content) {
        User user = userDAO.findById(uid).get();
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setContent(content);
        feedback.setCreateTime(new Date());
        return save(feedback) == null ? 1 : 0;
    }

    /**
     * find feedback by id
     * @param id feedback id
     * @return feedback
     */
    @Override
    public Feedback findById(Long id) {
        return feedbackDAO.findById(id).get();
    }

    @Override
    public List<FeedbackVo> findAll() {
        return feedbackDAO.findAllFeedbackVo();
    }

}