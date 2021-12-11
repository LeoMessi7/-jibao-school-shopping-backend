package com.t09.jibao.service;
import com.t09.jibao.domain.Comment;
import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.User;
import javafx.util.Pair;

import java.util.List;

public interface UploadService {
    Upload save(Upload upload);

    Upload findById(Long id);

    Upload findByGid(Long gid);

    Pair<List<User>, List<List<Comment>>> findSellersInfoListByGoodsList(List<Goods> goodsList);

    List<Goods> findUploadGoods(Long uid);
}
