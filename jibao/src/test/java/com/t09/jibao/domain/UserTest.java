package com.t09.jibao.domain;

import com.t09.jibao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
class UserTest {
    @Autowired
    private UserService userService;
    @Test
    void testInsert() throws IOException {
        User user = new User();
        user.setAvatar_path("123");
        user.setEmail("123");
        user.setName("123");
        user.setPassword("123");
        user.setBalance(10);
        Date date = new Date();
        user.setCreate_time(date);
        userService.save(user);
        userService.activate(23L);
    }

    @Test
    void testQuery(){
        Long id = 1L;
        User user = userService.findById(id);
        System.out.println(user.getBalance());
    }

    @Test
    void testQueryEmail() throws IOException {
        String email = "1391909128@qq.com";
        User user = userService.findByEmail(email);
        System.out.println(user.getBalance());
        System.in.read();
    }

}
