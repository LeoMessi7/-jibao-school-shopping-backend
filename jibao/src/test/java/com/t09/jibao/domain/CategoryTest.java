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
        Category category = new Category();
        category.setCategory("book");
        category.setSub_category("math");
        category.setDescription("123");
        category.setImage_path("123");
        category.setTotal_sold(0);
        category.setId(5L);
        System.out.println(category);
        categoryService.save(category);
    }

    @Test
    void testQuery(){
        System.out.println(categoryService.findById(1L));
    }
}
