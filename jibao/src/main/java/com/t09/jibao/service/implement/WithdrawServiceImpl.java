package com.t09.jibao.service.implement;

import com.t09.jibao.dao.GoodsDAO;
import com.t09.jibao.dao.UploadDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.dao.WithdrawDAO;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.User;
import com.t09.jibao.domain.Withdraw;
import com.t09.jibao.service.GoodsService;
import com.t09.jibao.service.UploadService;
import com.t09.jibao.service.UserService;
import com.t09.jibao.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private WithdrawDAO withdrawDAO;
    @Autowired
    private GoodsDAO goodsDAO;

    @Override
    public Withdraw save(Withdraw withdraw) {
        return withdrawDAO.save(withdraw);
    }

    @Override
    public Withdraw findById(Long id){
        Withdraw withdraw = withdrawDAO.findById(id).get();
        return withdraw;
    }

    @Override
    public Withdraw findByGid(Long gid){
        Goods goods = goodsDAO.findById(gid).get();
        Withdraw withdraw = withdrawDAO.findWithdrawByGoods(goods).get(0);
        return withdraw;
    }
}