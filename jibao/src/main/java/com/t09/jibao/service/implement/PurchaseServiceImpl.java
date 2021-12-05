package com.t09.jibao.service.implement;

import com.t09.jibao.dao.PurchaseDAO;
import com.t09.jibao.dao.SelectionDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Purchase;
import com.t09.jibao.domain.Selection;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.PurchaseService;
import com.t09.jibao.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDAO purchaseDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseDAO.save(purchase);
    }

    @Override
    public Purchase findById(Long id){
        return null;
    }

    @Override
    public List<Purchase> findByUid(Long uid){
        User user = userDAO.findById(uid).get();
        return purchaseDAO.findPurchaseByUser(user);
    }
}