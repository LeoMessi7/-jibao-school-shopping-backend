package com.t09.jibao.dao;

import com.t09.jibao.Vo.GoodsVo;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadDAO extends JpaRepository<Upload, Long>  {

    Upload findFirstByGoods(Goods goods);

    @Query(value = "select new com.t09.jibao.Vo.GoodsVo(g, p, u) from upload up left join goods g on up.goods.id = g.id left join purchase p on p.goods.id = g.id left join user u on p.user.id = u.id  where up.user.id=:uid")
    List<GoodsVo> findAllGoodsVoByUid(Long uid);

    List<Upload> findAllByUser(User user);

}
