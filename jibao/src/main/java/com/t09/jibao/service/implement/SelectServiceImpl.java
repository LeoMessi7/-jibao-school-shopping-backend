package com.t09.jibao.service.implement;

import com.t09.jibao.dao.FeedbackDAO;
import com.t09.jibao.dao.SelectDAO;
import com.t09.jibao.domain.Feedback;
import com.t09.jibao.domain.Select;
import com.t09.jibao.service.FeedbackService;
import com.t09.jibao.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;

public class SelectServiceImpl implements SelectService {

    @Autowired
    private SelectDAO selectDAO;

    @Override
    public Select save(Select select) {
        return selectDAO.save(select);
    }
}
