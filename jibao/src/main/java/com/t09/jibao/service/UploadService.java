package com.t09.jibao.service;
import com.t09.jibao.Vo.GoodsVo;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.User;
import javafx.util.Pair;

import java.util.List;

public interface UploadService {
    // save
    Upload save(Upload upload);

    // find by upload id
    Upload findById(Long id);

    // find by goods id
    Upload findByGid(Long gid);

    // find seller information by goods id
    Pair<User, List<Comment>> findSellersInfoByGid(Long gid);

    // find goods info by user id
    List<GoodsVo> findGoodsVoInfoByUid(Long uid);
}
