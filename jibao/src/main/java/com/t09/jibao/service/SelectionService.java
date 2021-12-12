package com.t09.jibao.service;

import com.t09.jibao.Vo.SelectionVo;
import com.t09.jibao.domain.Selection;

import java.util.List;

public interface SelectionService {
    Selection save(Selection selection);

    Selection findById(Long id);

    List<SelectionVo> findByUid(Long uid);

    int select(Long uid, Long gid);

}
