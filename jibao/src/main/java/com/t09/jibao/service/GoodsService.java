package com.t09.jibao.service;

import com.t09.jibao.domain.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GoodsService {

    // save
    Goods save(Goods goods);

    // find by good id
    Goods findById(Long id);

    // search by key word
    List<Goods> search(String content);

    // add goods
    Goods add(Long uid, String sub_category, String name,
              int price, String description, String campus, MultipartFile image) throws IOException;

    // update
    String update(Long uid, Long gid, String sub_category, String name,
                 int price, String description, String campus, MultipartFile image) throws IOException;

}
