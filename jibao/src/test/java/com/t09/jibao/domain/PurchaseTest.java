package com.t09.jibao.domain;

import com.t09.jibao.dao.PurchaseDAO;
import com.t09.jibao.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@SpringBootTest
class PurchaseTest {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

    @Test
    void testInsert(){
        Purchase purchase = new Purchase();
        purchase.setPurchase_time(new Date());
        purchase.setGoods(goodsService.findById(1L));
        purchase.setUser(userService.findById(1L));
        purchaseService.save(purchase);
    }



}
