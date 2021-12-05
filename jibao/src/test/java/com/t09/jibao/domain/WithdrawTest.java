package com.t09.jibao.domain;
import com.t09.jibao.service.GoodsService;
import com.t09.jibao.service.UploadService;
import com.t09.jibao.service.UserService;
import com.t09.jibao.service.WithdrawService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class WithdrawTest {
    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

    @Test
    void testInsert(){
        Withdraw withdraw = new Withdraw();
        Goods goods = goodsService.findById(1L);
        User user = userService.findById(1L);
        withdraw.setWithdraw_time(new Date());
        withdraw.setUser(user);
        withdraw.setGoods(goods);
        withdrawService.save(withdraw);
    }

    @Test
    void testQueryByGid(){
        System.out.println(withdrawService.findByGid(1L));
    }

}
