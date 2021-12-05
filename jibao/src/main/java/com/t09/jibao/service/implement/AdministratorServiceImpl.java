package com.t09.jibao.service.implement;

import com.t09.jibao.dao.AdministratorDAO;
import com.t09.jibao.domain.Administrator;
import com.t09.jibao.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorDAO administratorDAO;

    @Override
    public Administrator save(Administrator administrator) {
        return administratorDAO.save(administrator);
    }

    @Override
    public Administrator findById(Long id){
        return administratorDAO.findById(id).get();
    }
}
