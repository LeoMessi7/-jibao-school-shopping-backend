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

    @Override
    public Upload save(Upload upload) {
        return uploadDAO.save(upload);
    }

    @Override
    public Upload findById(Long id){
        return uploadDAO.findById(id).get();
    }

    @Override
    public Upload findByGid(Long gid){
        Goods goods = goodsDAO.findById(gid).get();
        return uploadDAO.findFirstByGoods(goods);
    }

    @Override
    public Pair<List<User>, List<List<Comment>>> findSellersInfoListByGoodsList(List<Goods> goodsList) {
        List<User> sellers = new ArrayList<>();
        List<List<Comment>> comments = new ArrayList<>();
        for(Goods goods: goodsList){
            User seller = uploadDAO.findFirstByGoods(goods).getUser();
            sellers.add(seller);
            comments.add(commentDAO.findCommentBySid(seller.getId()));
        }
        return new Pair<>(sellers, comments);
    }

    @Override
    public List<Goods> findUploadGoods(Long uid) {
        User user = userDAO.findById(uid).get();
        List<Upload> uploads = uploadDAO.findAllByUser(user);
        return uploads.stream().map(Upload::getGoods).filter(goods -> goods.getStatus() < 2).collect(Collectors.toList());
    }

    @Override
    public List<GoodsVo> findGoodsVoInfoByUid(Long uid){
        return uploadDAO.findAllGoodsVoByUid(uid);
    }

}