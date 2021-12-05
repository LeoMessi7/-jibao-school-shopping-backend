package com.t09.jibao.domain;

import com.t09.jibao.service.AdministratorService;
import com.t09.jibao.service.FeedbackService;
import com.t09.jibao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class FeedbackTest {
    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;
    @Test
    void testInsert(){
        User user = userService.findById(1L);
        Feedback feedback = new Feedback();
        feedback.setContent("hello");
        feedback.setCreate_time(new Date());
        feedback.setUser(user);
        feedbackService.save(feedback);
    }
}
