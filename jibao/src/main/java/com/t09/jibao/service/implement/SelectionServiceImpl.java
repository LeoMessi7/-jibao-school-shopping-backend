package com.t09.jibao.service.implement;

import com.t09.jibao.Vo.SelectionVo;
import com.t09.jibao.dao.GoodsDAO;
import com.t09.jibao.dao.SelectionDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Selection;
import com.t09.jibao.domain.SelectionPK;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SelectionServiceImpl implements SelectionService {

    @Autowired
    private SelectionDAO selectionDAO;

    @Autowired
    private GoodsDAO goodsDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public Selection save(Selection selection) {
        return selectionDAO.save(selection);
    }

    @Override
    public Selection findById(Long id){
        return null;
    }

    @Override
    public List<SelectionVo> findByUid(Long uid){
        return selectionDAO.findSelectionByUid(uid);
    }

    @Override
    public int select(Long uid, Long gid) {
        Goods goods = goodsDAO.findById(gid).get();
        if(goods.getStatus()!=0)
            return 1;
        User user = userDAO.findById(uid).get();
        SelectionPK selectionPK = new SelectionPK();
        selectionPK.setUser(user);
        selectionPK.setGoods(goods);
        Selection selection = new Selection();
        selection.setPk(selectionPK);
        selection.setSelectTime(new Date());
        save(selection);
        return 0;
    }

    @Override
    public void delete(Long uid, Long gid) {
        User user = userDAO.findById(uid).get();
        Goods goods = goodsDAO.findById(gid).get();
        SelectionPK selectionPK = new SelectionPK();
        selectionPK.setGoods(goods);
        selectionPK.setUser(user);
        selectionDAO.deleteById(selectionPK);
    }


}