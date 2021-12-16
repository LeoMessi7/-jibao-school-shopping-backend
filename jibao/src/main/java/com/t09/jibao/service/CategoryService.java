package com.t09.jibao.service;


import com.t09.jibao.domain.Category;
import java.util.List;
import java.util.Map;

public interface CategoryService {

    // save
    Category save(Category category);

    // find by category id
    Category findById(Long id);

    // add category
    int add(String category, String sub_category, String description);

    // search category
    List<Category> search(String content);

    // find by sub category
    Category findBySubCategory(String sub_category);

    // find all
    Map<String, List<String>> findAll();

    // delete
    void deleteCategory(String sub_category);

    // update
    int updateCategory(String category, String sub_category, String description);

    List<Category> getCategory();
}
