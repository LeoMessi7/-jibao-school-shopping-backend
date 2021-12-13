package com.t09.jibao.service.implement;

import com.t09.jibao.dao.ChatDAO;
import com.t09.jibao.dao.CommentDAO;
import com.t09.jibao.dao.DealtDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.CommentPK;
import com.t09.jibao.domain.Dealt;
import com.t09.jibao.service.ChatService;
import com.t09.jibao.service.CommentService;
import com.t09.jibao.service.DealtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public Comment save(Comment comment) {
        return commentDAO.save(comment);
    }

    @Override
    public List<Comment> findBySid(Long sid){
        return commentDAO.findCommentBySid(sid);
    }

    @Override
    public int add(Long uid, String seller_name, String content, double mark) {
        Comment comment = new Comment();
        CommentPK commentPK = new CommentPK();
        commentPK.setSeller(userDAO.findFirstByName(seller_name));
        commentPK.setBuyer(userDAO.findById(uid).get());
        comment.setPk(commentPK);
        comment.setCommentTime(new Date());
        comment.setContent(content);
        comment.setMark(mark);
        return save(comment) == null ? 1 : 0;
    }

    @Override
    public int check(Long uid, String seller_name) {
        CommentPK commentPK = new CommentPK();
        commentPK.setSeller(userDAO.findFirstByName(seller_name));
        commentPK.setBuyer(userDAO.findById(uid).get());
        Optional<Comment> comment = commentDAO.findById(commentPK);
        return comment.isPresent() ? 1 : 0;
    }
}