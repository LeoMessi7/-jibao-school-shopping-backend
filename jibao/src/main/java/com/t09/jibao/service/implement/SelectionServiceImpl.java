package com.t09.jibao.service.implement;

import com.t09.jibao.dao.SelectionDAO;
import com.t09.jibao.domain.Selection;
import com.t09.jibao.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectionServiceImpl implements SelectionService {

    @Autowired
    private SelectionDAO selectionDAO;

    @Override
    public Selection save(Selection selection) {
        return selectionDAO.save(selection);
    }

    @Override
    public Selection findById(Long id){
        return null;
    }

    @Override
    public List<Selection> findByUid(Long uid){
        return selectionDAO.findSelectionByUid(uid);
    }
}