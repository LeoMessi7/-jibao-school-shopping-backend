package com.t09.jibao.service;

import com.t09.jibao.domain.User;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {
    User save(User user);

    User findById(Long id);

    User findByEmail(String email);

    User create(String email, String name, String password);

    User activate(Long uid) throws IOException;

    User updateAvatar(Long uid) throws FileNotFoundException;

    String getAvatarPath(Long uid);
}
