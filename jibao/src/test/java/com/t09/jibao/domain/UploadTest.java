package com.t09.jibao.domain;
import com.t09.jibao.service.GoodsService;
import com.t09.jibao.service.UploadService;
import com.t09.jibao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class UploadTest {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

    @Test
    void testInsert(){
        Upload upload = new Upload();
        Goods goods = goodsService.findById(1L);
        User user = userService.findById(1L);
        upload.setUploadTime(new Date());
        upload.setUser(user);
        upload.setGoods(goods);
        uploadService.save(upload);
    }

    @Test
    void testQueryByGid(){
        System.out.println(uploadService.findByGid(1L));
    }

}
