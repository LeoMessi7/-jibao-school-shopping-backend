package com.t09.jibao.service.implement;

import com.t09.jibao.Vo.GoodsVo;
import com.t09.jibao.dao.CommentDAO;
import com.t09.jibao.dao.GoodsDAO;
import com.t09.jibao.dao.UploadDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.GoodsService;
import com.t09.jibao.service.UploadService;
import com.t09.jibao.service.UserService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadDAO uploadDAO;
    @Autowired
    private GoodsDAO goodsDAO;
    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * save
     * @param upload object
     * @return upload object
     */
    @Override
    public Upload save(Upload upload) {
        return uploadDAO.save(upload);
    }

    /**
     * find by id
     * @param id upload id
     * @return upload
     */
    @Override
    public Upload findById(Long id){
        return uploadDAO.findById(id).get();
    }

    /**
     * find by goods id
     * @param gid goods id
     * @return goods
     */
    @Override
    public Upload findByGid(Long gid){
        Goods goods = goodsDAO.findById(gid).get();
        return uploadDAO.findFirstByGoods(goods);
    }

    /**
     * find seller information
     * @param gid goods id
     * @return seller information
     */
    @Override
    public Pair<User, List<Comment>> findSellersInfoByGid(Long gid) {
        Goods goods = goodsDAO.findById(gid).get();
        User seller = uploadDAO.findFirstByGoods(goods).getUser();
        List<Comment> comments = commentDAO.findCommentBySid(seller.getId());
        return new Pair<>(seller, comments);
    }


    @Override
    public List<GoodsVo> findGoodsVoInfoByUid(Long uid){
        return uploadDAO.findAllGoodsVoByUid(uid);
    }

}