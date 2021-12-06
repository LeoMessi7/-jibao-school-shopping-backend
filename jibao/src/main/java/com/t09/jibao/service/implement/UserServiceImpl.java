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
        user.setAvatar_path("null");
        user.setCreate_time(new Date());
        user = userDAO.save(user);
        return user;
    }

    @Override
    public User activate(Long uid) throws IOException {
        User user = findById(uid);
        String avatar_dir_path = String.format("src/main/resources/static/images/avatar/%d", uid);
        File file = new File(avatar_dir_path);
        if(!file.exists())
            file.mkdir();
        File common_avatar_file = new File("src\\main\\resources\\static\\images\\common\\avatar\\avatar.png");
        File user_avatar_file = new File(String.format("src\\main\\resources\\static\\images\\avatar\\%d\\avatar.png", uid));
        if(!user_avatar_file.exists())
            Files.copy(common_avatar_file.toPath(), user_avatar_file.toPath());
        user.setAvatar_path(user_avatar_file.getPath().substring(19));
        user.set_active(true);
        return save(user);
    }

    @Override
    public User updateAvatar(Long uid) throws FileNotFoundException {
        User user = findById(uid);
        File file = new File(user.getAvatar_path());
        InputStream inputStream = new FileInputStream(file);

        return save(user);
    }

    @Override
    public String getAvatarPath(Long uid){
        User user = findById(uid);
        return user.getAvatar_path();
    }

}