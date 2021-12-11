package com.t09.jibao.dao;

import com.t09.jibao.domain.Goods;
import com.t09.jibao.domain.Upload;
import com.t09.jibao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadDAO extends JpaRepository<Upload, Long>  {

    Upload findFirstByGoods(Goods goods);

    List<Upload> findAllByUser(User user);

}
