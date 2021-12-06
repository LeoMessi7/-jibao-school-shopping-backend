package com.t09.jibao.service;


import com.t09.jibao.domain.Category;
import com.t09.jibao.domain.Goods;

public interface CategoryService {

    Category save(Category category);

    Category findById(Long id);

    Category create(String category, String sub_category, String description);
}
