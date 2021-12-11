package com.t09.jibao.dao;

import com.t09.jibao.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {
    List<Category> findAllByCategoryLike(String category);
    List<Category> findAllBySubCategoryLike(String sub_category);
    Category findFirstBySubCategory(String sub_category);
    List<Category> findAll();
}
