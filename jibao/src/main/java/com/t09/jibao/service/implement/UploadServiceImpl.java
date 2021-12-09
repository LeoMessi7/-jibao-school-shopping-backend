package com.t09.jibao.service.implement;

import com.t09.jibao.dao.GoodsDAO;
import com.t09.jibao.dao.UploadDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.GoodsService;
import com.t09.jibao.service.UploadService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadDAO uploadDAO;
    @Autowired
    private GoodsDAO goodsDAO;

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
}