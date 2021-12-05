package com.t09.jibao.domain;

import com.t09.jibao.service.CategoryService;
import com.t09.jibao.service.GoodsService;
import com.t09.jibao.service.SelectionService;
import com.t09.jibao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@SpringBootTest
class SelectionTest {
    @Autowired
    private SelectionService selectionService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

    @Test
    void testInsert(){
        Selection selection = new Selection();
        selection.setSelect_time(new Date());
        SelectionPK selectionPK = new SelectionPK();
        selectionPK.setGoods(goodsService.findById(2L));
        selectionPK.setUser(userService.findById(1L));
        selection.setPk(selectionPK);
        selectionService.save(selection);
    }

    @Test
    void testQuery() {
        Long uid = 1L;
        List<Selection> selections = selectionService.findByUid(uid);
        System.out.println(selections.size());
        System.out.println(selections.get(0).getSelect_time());
        System.out.println(selections.get(1).getSelect_time());
    }

}
