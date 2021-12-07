package com.t09.jibao.service.implement;

import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private String common_avatar_path = "jibao/src/main/resources/static/images/common/avatar/avatar.png";
    private String avatar_path_template = "jibao/src/main/resources/static/images/avatar/%d";

    @Autowired
    private UserDAO userDAO;


    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public User findById(Long id){
        User user = userDAO.findById(id).get();
        return user;
    }

    @Override
    public User findByEmail(String email){
        return userDAO.findFirstByEmail(email);
    }

    @Override
    public User create(String email, String name, String password){
        User user = findByEmail(email);
        if(user == null)
            user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setBalance(0);
        user.setAvatarPath("null");
        user.setCreateTime(new Date());
        user = userDAO.save(user);
        return user;
    }

    @Override
    public User activate(Long uid) throws IOException {
        User user = findById(uid);
        String avatar_dir_path = String.format(avatar_path_template, uid);
        File file = new File(avatar_dir_path);
        if(!file.exists()) {
            file.mkdir();
        }
        File common_avatar_file = new File(common_avatar_path);
        String avatar_path = String.format(avatar_path_template + "/avatar.png", uid);
        File user_avatar_file = new File(avatar_path);
        if(!user_avatar_file.exists())
            Files.copy(common_avatar_file.toPath(), user_avatar_file.toPath());
        System.out.println(user_avatar_file.getCanonicalPath());
        user.setAvatarPath(avatar_path.substring(25));
        user.setActive(true);
        return save(user);
    }

    @Override
    public User updateAvatar(Long uid) throws FileNotFoundException {
        User user = findById(uid);
        File file = new File(user.getAvatarPath());
        InputStream inputStream = new FileInputStream(file);

        return save(user);
    }

    @Override
    public String getAvatarPath(Long uid){
        User user = findById(uid);
        return user.getAvatarPath();
    }

}