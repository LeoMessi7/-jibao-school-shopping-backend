package com.t09.jibao.service.implement;

import com.t09.jibao.dao.CaptchaDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Captcha;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private CaptchaDAO captchaDAO;

    @Value("${expiredTime}")
    private int expiredTime;


    /**
     * save
     * @param user user object
     * @return user object
     */
    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public User findById(Long id){
        return userDAO.findById(id).get();
    }

    /**
     * find user by email
     * @param email user email
     * @return user object
     */
    @Override
    public User findByEmail(String email){
        return userDAO.findFirstByEmail(email);
    }

    /**
     * create a new user
     * @param email user email
     * @param name user name
     * @param password user password
     * @return user object
     */
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

    /**
     * activate account
     * @param email user email
     * @param captcha_input captcha
     * @return error code
     */
    @Override
    public int activate(String email, String captcha_input) throws IOException {
        User user = findByEmail(email);
        if(user == null)
            return 1;
        Captcha captcha = captchaDAO.findFirstByUser(user);
        Date time_limit = new Date(captcha.getCreate_time().getTime() + expiredTime);
        // out of time
        if(captcha.getCreate_time().after(time_limit))
            return 2;
        // not equal
        if(!captcha.getEmail_captcha().equals(captcha_input))
            return 3;

        Long uid = user.getId();
        String avatar_dir_path = String.format(avatar_path_template, uid);
        File file = new File(avatar_dir_path);
        // make a new directory for the new user
        if(!file.exists()) {
            file.mkdir();
        }
        File common_avatar_file = new File(common_avatar_path);
        String avatar_path = String.format(avatar_path_template + "/avatar.png", uid);
        File user_avatar_file = new File(avatar_path);
        // create a new avatar.png
        if(!user_avatar_file.exists())
            Files.copy(common_avatar_file.toPath(), user_avatar_file.toPath());
        System.out.println(user_avatar_file.getCanonicalPath());
        user.setAvatarPath(avatar_path.substring(25));
        user.setActive(true);
        save(user);
        return 0;
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