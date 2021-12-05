package com.t09.jibao.service.implement;

import com.t09.jibao.dao.ChatDAO;
import com.t09.jibao.dao.DealtDAO;
import com.t09.jibao.domain.Chat;
import com.t09.jibao.domain.Dealt;
import com.t09.jibao.service.ChatService;
import com.t09.jibao.service.DealtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealtServiceImpl implements DealtService {

    @Autowired
    private DealtDAO dealtDAO;

    @Override
    public Dealt save(Dealt dealt) {
        return dealtDAO.save(dealt);
    }
}