package com.t09.jibao.service.implement;


import com.t09.jibao.dao.CategoryDAO;
import com.t09.jibao.domain.Category;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    /**
     * save
     * @param category category object
     * @return category object
     */
    @Override
    public Category save(Category category) {
        return categoryDAO.save(category);
    }

    /**
     * find category by id
     * @param id category id
     * @return category
     */
    @Override
    public Category findById(Long id) {
        return categoryDAO.findById(id).get();
    }

    /**
     * add category
     * @param category category
     * @param sub_category sub category
     * @param description description
     * @return error code
     */
    @Override
    public int add(String category, String sub_category, String description){
        Category cate = new Category();
        cate.setCategory(category);
        cate.setSubCategory(sub_category);
        cate.setDescription(description);
        return save(cate) == null ? 1 : 0;
    }

    /**
     * search
     * @param content key word
     * @return categories
     */
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

    /**
     * find category by sub_category
     * @param sub_category sub category
     * @return category
     */
    @Override
    public Category findBySubCategory(String sub_category){
        return categoryDAO.findFirstBySubCategory(sub_category);
    }

    /**
     * find all
     * @return all categories
     */
    @Override
    public Map<String, List<String>> findAll() {
        List<Category> categoryList = categoryDAO.findAll();
        return categoryList.stream().collect(Collectors.groupingBy(Category::getCategory, Collectors.mapping(Category::getSubCategory, Collectors.toList())));
    }

    /**
     * delete
     * @param sub_category sub category
     */
    @Override
    public void deleteCategory(String sub_category) {
        Category category = categoryDAO.findFirstBySubCategory(sub_category);
        categoryDAO.delete(category);
    }

    /**
     * update
     * @param category category
     * @param sub_category sub category
     * @param description description
     * @return error code
     */
    @Override
    public int updateCategory(Long cid, String category, String sub_category, String description) {
        Category cate = categoryDAO.findById(cid).get();
        cate.setCategory(category);
        cate.setSubCategory(sub_category);
        cate.setDescription(description);
        return save(cate) == null ? 1 : 0;
    }

    public List<Category> getCategory(){
        return categoryDAO.findAll();
    }

}