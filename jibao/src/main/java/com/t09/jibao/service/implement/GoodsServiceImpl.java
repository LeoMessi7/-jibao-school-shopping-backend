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

    /**
     * save
     * @param goods goods
     * @return goods
     */
    @Override
    public Goods save(Goods goods) {
        return goodsDAO.save(goods);
    }

    /**
     * find goods by id
     * @param id goods id
     * @return goods
     */
    @Override
    public Goods findById(Long id) {
        return goodsDAO.findById(id).get();
    }

    /**
     * search goods
     * @param content key word
     * @return list
     */
    @Override
    public List<Goods> search(String content)
    {
        List<Category> categories = categoryService.search(content);
        content = content.replace("", "%").trim();
        content = "%" + content + "%";
        List<Goods> goodsList1 = goodsDAO.findAllByCategoryInAndStatusEquals(categories, 0);
        List<Goods> goodsList2 = goodsDAO.findAllByNameLikeAndStatusEquals(content, 0);
        List<Goods> goodsList3 = goodsDAO.findAllByCampusLikeAndStatusEquals(content, 0);
        List<Goods> goodsList4 = goodsDAO.findAllByDescriptionLikeAndStatusEquals(content, 0);
        goodsList1.removeAll(goodsList2);
        goodsList1.addAll(goodsList2);
        goodsList3.removeAll(goodsList4);
        goodsList3.addAll(goodsList4);
        goodsList1.removeAll(goodsList3);
        goodsList1.addAll(goodsList3);
        return goodsList1;
    }

    /**
     * add goods
     * @param uid user id
     * @param sub_category sub category
     * @param name goods name
     * @param price goods price
     * @param description goods description
     * @param campus campus
     * @param image image
     * @return goods
     */
    @Override
    public Goods add(Long uid, String sub_category, String name,
                     int price, String description,String campus, MultipartFile image) throws IOException {
        Goods goods = new Goods();
        Category cate = categoryService.findBySubCategory(sub_category);
        goods.setCategory(cate);
        goods.setDescription(description);
        goods.setPrice(price);
        goods.setStatus(0);
        goods.setImagePath("null");
        goods.setName(name);
        goods.setCampus(campus);
        goods = save(goods);
        String goods_dir_path = String.format(goods_path_template, goods.getId());
        File file = new File(goods_dir_path);
        if(!file.exists()) {
            file.mkdir();
        }
        File common_goods_file = new File(common_goods_path);
        String goods_path = String.format(goods_path_template + "/goods.png", goods.getId());
        File goods_image_file = new File(goods_path);
        // copy image
        if(image == null){
            if(!goods_image_file.exists())
                Files.copy(common_goods_file.toPath(), goods_image_file.toPath());
        }
        else {
            String image_name = image.getOriginalFilename();
            int split_index = image_name.lastIndexOf(".");
            String suffix = image_name.substring(split_index + 1);
            // check
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
        upload.setUploadTime(new Date());
        User user = userDAO.findById(uid).get();
        upload.setUser(user);
        uploadDAO.save(upload);
        return goods;
    }



    @Override
    public String update(Long uid, Long gid, String sub_category, String name,
                     int price, String description, String campus, MultipartFile image) throws IOException {
        System.out.println(image);
        Goods goods = goodsDAO.findById(gid).get();
        Category cate = categoryService.findBySubCategory(sub_category);
        goods.setCategory(cate);
        goods.setDescription(description);
        goods.setName(name);
        goods.setPrice(price);
        goods.setCampus(campus);
        if(image == null) {
            save(goods);
            return goods.getImagePath();
        }

        String image_name = image.getOriginalFilename();
        System.out.println(image_name);
        // ignore this warning
        // because the image must exist
        int split_index = image_name.lastIndexOf(".");
        String suffix = image_name.substring(split_index + 1);
        System.out.println(suffix);
        String goods_path = String.format(goods_path_template + "/goods.png", gid);
        if ("jpg".equals(suffix) || "jpeg".equals(suffix) || "png".equals(suffix)) {
            goods_path = goods_path.substring(0, goods_path.lastIndexOf(".")) + "." + suffix;
            File goods_image_file = new File(goods_path);
            goods_image_file = new File(goods_image_file.getAbsolutePath());
            image.transferTo(goods_image_file);
            goods.setImagePath(goods_path.substring(25));
            save(goods);
            return goods.getImagePath();
        } else {
            return "";
        }
    }
}
