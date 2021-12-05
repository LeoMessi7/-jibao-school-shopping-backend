package com.t09.jibao.domain;

import com.t09.jibao.service.AdministratorService;
import com.t09.jibao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class UserTest {
    @Autowired
    private UserService userService;
    @Test
    void testInsert(){
        User user = new User();
        user.setAvatar_path("123");
        user.setEmail("123");
        user.setName("123");
        user.setPassword("123");
        user.setBalance(10);
        Date date = new Date();
        user.setCreate_time(date);
        userService.save(user);
    }

    @Test
    void testQuery(){
        Long id = 1L;
        User user = userService.findById(id);
        System.out.println(user.getBalance());
    }

}
