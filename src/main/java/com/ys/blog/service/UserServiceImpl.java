package com.ys.blog.service;

import com.ys.blog.dao.UserRepository;
import com.ys.blog.po.User;
import com.ys.blog.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,
                DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }
}
