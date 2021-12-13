package com.t09.jibao.domain;

import com.t09.jibao.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class DealtTest {
    @Autowired
    private DealtService dealtService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private FeedbackService feedbackService;
    @Test
    void testInsert(){
        Administrator administrator = administratorService.findById(1L);
        Dealt dealt = new Dealt();
        dealt.setDealt_time(new Date());
        dealt.setAdministrator(administrator);
        dealt.setContent("holl");
        dealt.setFeedback(feedbackService.findById(1L));
        dealtService.save(dealt);
    }

}
