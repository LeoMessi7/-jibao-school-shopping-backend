package com.t09.jibao.service.implement;

import com.t09.jibao.dao.*;
import com.t09.jibao.domain.*;
import com.t09.jibao.service.ChatService;
import com.t09.jibao.service.DealtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DealtServiceImpl implements DealtService {

    @Autowired
    private DealtDAO dealtDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private AdministratorDAO administratorDAO;

    /**
     * save
     * @param dealt object
     * @return dealt object
     */
    @Override
    public Dealt save(Dealt dealt) {
        return dealtDAO.save(dealt);
    }

    /**
     * add dealt
     * @param user_name user name
     * @param aid administrator id
     * @param fid feedback id
     * @param content content
     */
    @Override
    public void add(String user_name, Long aid, Long fid, String content) {
        User user = userDAO.findFirstByName(user_name);
        Administrator administrator = administratorDAO.findById(aid).get();
        Feedback feedback = feedbackDAO.findById(fid).get();
        Dealt dealt = new Dealt();
        dealt.setContent(content);
        dealt.setDealtTime(new Date());
        dealt.setAdministrator(administrator);
        dealt.setFeedback(feedback);
        feedback.setStatus(1);
        feedbackDAO.save(feedback);
        save(dealt);
        feedbackDAO.save(feedback);
    }
}