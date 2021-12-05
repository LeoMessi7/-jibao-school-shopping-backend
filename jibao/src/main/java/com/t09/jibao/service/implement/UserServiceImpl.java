package com.t09.jibao.service.implement;

import com.t09.jibao.dao.AdministratorDAO;
import com.t09.jibao.dao.UserDAO;
import com.t09.jibao.domain.Administrator;
import com.t09.jibao.domain.User;
import com.t09.jibao.service.AdministratorService;
import com.t09.jibao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        System.out.println(666);
        System.out.println(user.getBalance());
        return user;
    }
}