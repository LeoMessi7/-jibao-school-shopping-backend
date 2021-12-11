package com.t09.jibao.service.implement;

import com.t09.jibao.dao.GoodsDAO;
import com.t09.jibao.dao.UploadDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Category;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.CategoryService;
import com.t09.jibao.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private String common_goods_path = "jibao/src/main/resources/static/images/common/goods/goods.png";
    private String goods_path_template = "jibao/src/main/resources/static/images/goods/%d";


    @Autowired
    private GoodsDAO goodsDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UploadDAO uploadDAO;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Goods save(Goods goods) {
        return goodsDAO.save(goods);
    }

    @Override
    public Goods findById(Long id) {
        return goodsDAO.findById(id).get();
    }


    @Override
    public List<Goods> search(String content)
    {
        List<Category> categories = categoryService.search(content);
        content = content.replace("", "%").trim();
        content = "%" + content + "%";
        List<Goods> goodsList1 = goodsDAO.findAllByCategoryInAndStatusEquals(categories, 0);
        List<Goods> goodsList2 = goodsDAO.findAllByNameLikeAndStatusEquals(content, 0);
        goodsList1.removeAll(goodsList2);
        goodsList1.addAll(goodsList2);
        return goodsList1;
    }


    @Override
    public Goods add(Long uid, String sub_category, String name,
                     int price, String description, MultipartFile image) throws IOException {
        Goods goods = new Goods();
        Category cate = categoryService.findBySubCategory(sub_category);
        goods.setCategory(cate);
        goods.setDescription(description);
        goods.setPrice(price);
        goods.setStatus(0);
        goods.setImagePath("null");
        goods = save(goods);
        String goods_dir_path = String.format(goods_path_template, goods.getId());
        File file = new File(goods_dir_path);
        if(!file.exists()) {
            file.mkdir();
        }
        File common_goods_file = new File(common_goods_path);
        String goods_path = String.format(goods_path_template + "/goods.png", goods.getId());
        File goods_image_file = new File(goods_path);
        if(image == null){
            if(!goods_image_file.exists())
                Files.copy(common_goods_file.toPath(), goods_image_file.toPath());
        }
        else {
            String image_name = image.getOriginalFilename();
            int split_index = image_name.lastIndexOf(".");
            String suffix = image_name.substring(split_index + 1, image_name.length());
            if ("jpg".equals(suffix) || "jpeg".equals(suffix) || "png".equals(suffix)) {
                goods_path = goods_path.substring(0, goods_path.lastIndexOf(".")) + "." + suffix;
                goods_image_file = new File(goods_path);
                goods_image_file = new File(goods_image_file.getAbsolutePath());
                image.transferTo(goods_image_file);
            }
            else{
                Files.copy(common_goods_file.toPath(), goods_image_file.toPath());
            }
        }
        goods.setImagePath(goods_path.substring(25));
        goods = save(goods);
        Upload upload = new Upload();
        upload.setGoods(goods);
        upload.setUpload_time(new Date());
        User user = userDAO.findById(uid).get();
        upload.setUser(user);
        uploadDAO.save(upload);
        return goods;
    }
}