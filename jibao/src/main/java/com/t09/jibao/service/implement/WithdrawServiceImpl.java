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

import java.util.Date;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private WithdrawDAO withdrawDAO;
    @Autowired
    private GoodsDAO goodsDAO;
    @Autowired
    private UserDAO userDAO;

    /**
     * save
     * @param withdraw withdraw object
     * @return withdraw object
     */
    @Override
    public Withdraw save(Withdraw withdraw) {
        return withdrawDAO.save(withdraw);
    }

    /**
     * find withdraw object by id
     * @param id the id of withdraw
     * @return withdraw object
     */
    @Override
    public Withdraw findById(Long id){
        return withdrawDAO.findById(id).get();
    }


    /**
     * find withdraw object by id of goods
     * @param gid goods id
     * @return withdraw object
     */
    @Override
    public Withdraw findByGid(Long gid){
        Goods goods = goodsDAO.findById(gid).get();
        return withdrawDAO.findWithdrawByGoods(goods).get(0);
    }

    /**
     * withdraw goods
     * @param uid user id
     * @param gid goods id
     * @return error code
     */
    @Override
    public int withdrawGoods(Long uid, Long gid){
        Goods goods = goodsDAO.findById(gid).get();
        // the goods have been sold or withdrew
        if(goods.getStatus() != 0)
            return 3;
        Withdraw withdraw = new Withdraw();
        User user = userDAO.findById(uid).get();
        goods.setStatus(2);
        withdraw.setGoods(goods);
        withdraw.setUser(user);
        withdraw.setWithdrawTime(new Date());
        goodsDAO.save(goods);
        save(withdraw);
        // success
        return 0;
    }
}