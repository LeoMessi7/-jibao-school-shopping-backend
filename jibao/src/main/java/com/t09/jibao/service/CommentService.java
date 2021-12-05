package com.t09.jibao.service;

import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.Dealt;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    List<Comment> findBySid(Long sid);
}
