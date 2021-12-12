package com.t09.jibao.service;

import com.t09.jibao.domain.Selection;

import java.util.List;

public interface SelectionService {
    Selection save(Selection selection);

    Selection findById(Long id);

    List<Selection> findByUid(Long uid);


}
