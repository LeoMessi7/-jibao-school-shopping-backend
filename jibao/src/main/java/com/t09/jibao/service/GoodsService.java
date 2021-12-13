package com.t09.jibao.service;

import com.t09.jibao.domain.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GoodsService {

    Goods save(Goods goods);

    Goods findById(Long id);

    List<Goods> search(String content);

    Goods add(Long uid, String sub_category, String name,
              int price, String description, String campus, MultipartFile image) throws IOException;

    String update(Long uid, Long gid, String sub_category, String name,
                 int price, String description, String campus, MultipartFile image) throws IOException;

}
