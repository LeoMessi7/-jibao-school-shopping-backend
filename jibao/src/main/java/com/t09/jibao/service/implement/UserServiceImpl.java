package com.t09.jibao.service.implement;

import com.t09.jibao.dao.CaptchaDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.*;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private CommentServiceImpl commentService;

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

    @Override
    public User findByName(String name) {
        return userDAO.findFirstByName(name);
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
        List<Captcha> captchaList = captchaDAO.findCaptchaByUser(user);
        Captcha captcha = captchaList.get(captchaList.size() - 1);
        Date time_limit = new Date(captcha.getCreate_time().getTime() + expiredTime);
        // out of time
        if(captcha.getCreate_time().after(time_limit))
            return 2;
        System.out.println(captcha.getEmail_captcha());
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
    public String updateAvatar(Long uid, MultipartFile avatar) throws IOException {
        User user = findById(uid);
        String image_name = avatar.getOriginalFilename();
        // ignore this warning
        // because the image must exist
        int split_index = image_name.lastIndexOf(".");
        String suffix = image_name.substring(split_index + 1);
        String avatar_path = String.format(avatar_path_template + "/avatar.png", uid);
        if ("jpg".equals(suffix) || "jpeg".equals(suffix) || "png".equals(suffix)) {
            avatar_path = avatar_path.substring(0, avatar_path.lastIndexOf(".")) + "." + suffix;
            File avatar_image_file = new File(avatar_path);
            avatar_image_file = new File(avatar_image_file.getAbsolutePath());
            avatar.transferTo(avatar_image_file);
            user.setAvatarPath(avatar_path.substring(25));
            save(user);
            return user.getAvatarPath();
        } else {
            return "";
        }
    }

    @Override
    public int changePassword(Long uid, String password){
        User user = findById(uid);
        user.setPassword(password);
        userDAO.save(user);
        return 0;
    }

    /**
     * get avatar path by name
     * @param name
     * @return
     */
    @Override
    public String getAvatarPathByName(String name) {
        System.out.println(name);
        User user = userDAO.findFirstByName(name);
        System.out.println(666);
        System.out.println(user);
        return user.getAvatarPath();
    }

    @Override
    public double getMark(Long uid){
        List<Comment> userCommentList = commentService.findBySid(uid);
        if (!userCommentList.isEmpty()){
            double totalMark = userCommentList.stream().mapToDouble(Comment::getMark).sum();
            return totalMark / userCommentList.size();
        }else {
            return 5;
        }
    }




}
