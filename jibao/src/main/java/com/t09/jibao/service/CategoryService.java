package com.t09.jibao.service;


import com.t09.jibao.domain.Category;

public interface CategoryService {

    Category save(Category category);

    Category findById(Long id);

}
