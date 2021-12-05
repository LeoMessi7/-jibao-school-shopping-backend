package com.t09.jibao.dao;

import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadDAO extends JpaRepository<Upload, Long>  {
    List<Upload> findUploadByGoods(Goods goods);
}
