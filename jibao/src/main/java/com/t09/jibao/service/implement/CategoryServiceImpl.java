package com.t09.jibao.service.implement;


import com.t09.jibao.dao.CategoryDAO;
import com.t09.jibao.domain.Category;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Category create(String category, String sub_category, String description){
        Category cate = new Category();
        cate.setCategory(category);
        cate.setSub_category(sub_category);
        cate.setDescription(description);
        return save(cate);
    }
}