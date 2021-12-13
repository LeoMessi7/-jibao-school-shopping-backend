package com.t09.jibao.dao;

import com.t09.jibao.Vo.GoodsVo;
import com.t09.jibao.domain.Category;
import com.t09.jibao.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDAO extends JpaRepository<Goods, Long> {
    List<Goods> findAllByCategoryInAndStatusEquals(List<Category> categories, int status);
    List<Goods> findAllByNameLikeAndStatusEquals(String content, int status);
    List<Goods> findAllByDescriptionLikeAndStatusEquals(String content, int status);
    List<Goods> findAllByCampusLikeAndStatusEquals(String content, int status);
    List<Goods> findAllByIdIn(List<Long> gid_list);
}
