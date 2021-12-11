package com.t09.jibao.service;


import com.t09.jibao.domain.Category;
import java.util.List;
import java.util.Map;

public interface CategoryService {

    Category save(Category category);

    Category findById(Long id);

    Category add(String category, String sub_category, String description);

    List<Category> search(String content);

    Category findByCategoryAndSubCategory(String category, String sub_category);

    Map<String, List<String>> findAll();

}
