package com.t09.jibao.service.implement;


import com.t09.jibao.dao.CategoryDAO;
import com.t09.jibao.domain.Category;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public Category save(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryDAO.findById(id).get();
    }

    @Override
    public Category add(String category, String sub_category, String description){
        Category cate = new Category();
        cate.setCategory(category);
        cate.setSubCategory(sub_category);
        cate.setDescription(description);
        return save(cate);
    }

    @Override
    public List<Category> search(String content){
        content = content.replace("", "%").trim();
        content = "%" + content + "%";
        List<Category> categories1 = categoryDAO.findAllByCategoryLike(content);
        List<Category> categories2 = categoryDAO.findAllBySubCategoryLike(content);
        categories1.removeAll(categories2);
        categories1.addAll(categories2);
        return categories1;
    }


    @Override
    public Category findByCategoryAndSubCategory(String category, String sub_category){
        return categoryDAO.findFirstByCategoryAndSubCategory(category, sub_category);
    }
}