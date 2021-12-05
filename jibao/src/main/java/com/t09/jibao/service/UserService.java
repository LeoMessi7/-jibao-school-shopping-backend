package com.t09.jibao.service;

import com.t09.jibao.domain.Administrator;
import com.t09.jibao.domain.User;

public interface UserService {
    User save(User user);

    User findById(Long id);
}
