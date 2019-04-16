package com.lj.service;

import com.lj.entity.User;

public interface UserService {

    //登录
    User login(User user);
    //注册
    User register(User user);

}
