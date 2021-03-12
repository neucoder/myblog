package com.ys.blog.service;

import com.ys.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
