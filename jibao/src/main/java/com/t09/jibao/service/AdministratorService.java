package com.t09.jibao.service;


import com.t09.jibao.domain.Administrator;


public interface AdministratorService {

    Administrator save(Administrator administrator);

    Administrator findById(Long id);

    Administrator findByEmail(String email);
}