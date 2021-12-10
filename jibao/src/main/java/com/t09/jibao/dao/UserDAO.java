package com.t09.jibao.dao;

import com.t09.jibao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findFirstByEmail(String email);
    User getFirstByEmail(String email);
    User findFirstByName(String username);
    List<User> findUserByEmail(String email);
}
