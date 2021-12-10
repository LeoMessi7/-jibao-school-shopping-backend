package com.t09.jibao.service;

import com.t09.jibao.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {
    // save
    User save(User user);

    // find user by id
    User findById(Long id);

    // find user by email
    User findByEmail(String email);

    // create a new user
    User create(String email, String name, String password);

    // activate account
    int activate(String email, String captcha_input) throws IOException;

    // update avatar
    String updateAvatar(Long uid, MultipartFile avatar) throws IOException;

    // get avatar url
    String getAvatarPath(Long uid);
}
