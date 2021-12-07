package com.t09.jibao.service;

import com.t09.jibao.domain.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GoodsService {

    Goods save(Goods goods);

    Goods findById(Long id);

    List<Goods> search(String content);

    Goods add(String category, String sub_category, String name,
              int price, String description, MultipartFile image) throws IOException;

}
