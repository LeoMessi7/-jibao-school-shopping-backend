package com.t09.jibao.domain;

import com.t09.jibao.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTest {
    @Autowired
    private CategoryService categoryService;
    @Test
    void testInsert(){

    }

    @Test
    void testQuery(){
        System.out.println(categoryService.findById(1L));
    }
}
