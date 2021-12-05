package com.t09.jibao.service.implement;

import com.t09.jibao.dao.AdministratorDAO;
import com.t09.jibao.dao.ChatDAO;
import com.t09.jibao.dao.GoodsDAO;
import com.t09.jibao.domain.Administrator;
import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.service.AdministratorService;
import com.t09.jibao.service.ChatService;
import com.t09.jibao.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDAO goodsDAO;

    @Override
    public Goods save(Goods goods) {
        return goodsDAO.save(goods);
    }

    @Override
    public Goods findById(Long id) {
        return goodsDAO.findById(id).get();
    }
}