package com.t09.jibao.domain;

import com.t09.jibao.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class CommentTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @Test
    void testInsert(){
        Comment comment = new Comment();
        comment.setComment_time(new Date());
        CommentPK commentPK = new CommentPK();
        commentPK.setBuyer(userService.findById(1L));
        commentPK.setSeller(userService.findById(2L));
        comment.setContent("hao");
        comment.setMark(5);
        comment.setPk(commentPK);
        commentService.save(comment);
    }

    @Test
    void testQuery(){
        commentService.findBySid(2L);
    }


}
