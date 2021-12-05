package com.t09.jibao.service.implement;

import com.t09.jibao.dao.ChatDAO;
import com.t09.jibao.dao.CommentDAO;
import com.t09.jibao.dao.DealtDAO;
import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.Dealt;
import com.t09.jibao.service.ChatService;
import com.t09.jibao.service.CommentService;
import com.t09.jibao.service.DealtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public Comment save(Comment comment) {
        return commentDAO.save(comment);
    }

    @Override
    public List<Comment> findBySid(Long sid){
        return commentDAO.findCommentBySid(sid);
    }
}