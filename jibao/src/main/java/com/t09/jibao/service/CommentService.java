package com.t09.jibao.service;

import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.Dealt;

import java.util.List;

public interface CommentService {
    // save
    Comment save(Comment comment);

    // find seller id
    List<Comment> findBySid(Long sid);

    // add comment
    int add(Long uid, String seller_name, String comment, double mark);

    // check
    int check(Long uid, String seller_name);
}
