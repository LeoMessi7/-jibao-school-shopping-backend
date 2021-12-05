package com.t09.jibao.domain;

import com.t09.jibao.service.AdministratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class AdministratorTest {
    @Autowired
    private AdministratorService administratorService;
    @Test
    void testInsert(){
        Administrator administrator = new Administrator();
        administrator.setAvatar_path("123");
        administrator.setEmail("123");
        administrator.setName("123");
        administrator.setPassword("123");
        Date date = new Date();
        System.out.println(date.getTime());
        administrator.setCreate_time(date);
        System.out.println(administrator);
        System.out.println(administratorService.save(administrator));
    }

    @Test
    void testQuery(){
        Administrator administrator = administratorService.findById(5L);
        System.out.println(administrator);
    }

}
