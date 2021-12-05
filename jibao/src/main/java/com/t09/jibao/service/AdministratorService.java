package com.t09.jibao.service;


import com.t09.jibao.domain.Administrator;
import com.t09.jibao.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdministratorService {

    Administrator save(Administrator administrator);

    Administrator findById(Long id);

}