package com.t09.jibao.service;

import com.t09.jibao.Vo.SelectionVo;
import com.t09.jibao.domain.Selection;

import java.util.List;

public interface SelectionService {
    // save
    Selection save(Selection selection);

    // find by selection id
    Selection findById(Long id);

    // find by user id
    List<SelectionVo> findByUid(Long uid);

    // add shopping cart
    int select(Long uid, Long gid);

    // delete shopping cart
    void delete(Long uid, Long gid);

}
