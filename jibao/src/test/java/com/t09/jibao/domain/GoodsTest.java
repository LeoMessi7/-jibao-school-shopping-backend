package com.t09.jibao.domain;

import com.t09.jibao.service.CategoryService;
import com.t09.jibao.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class GoodsTest {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CategoryService categoryService;

    @Test
    void testInsert(){
        Goods goods = new Goods();
        goods.setStatus(1);
        goods.setName("jjlk");
        goods.setDescription("hello");
        goods.setImage_path("123");
        Category category = categoryService.findById(1L);
        goods.setCategory(category);
        goods.setPrice(12);
        goodsService.save(goods);
    }

    @Test
    void testQuery(){
        System.out.println(goodsService.findById(1L));
    }

}
