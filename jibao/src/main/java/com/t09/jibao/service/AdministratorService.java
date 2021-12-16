package com.t09.jibao.service;


import com.t09.jibao.domain.Administrator;


public interface AdministratorService {

    // save
    Administrator save(Administrator administrator);

    // find administrator by id
    Administrator findById(Long id);

    // find administrator by email
    Administrator findByEmail(String email);
}