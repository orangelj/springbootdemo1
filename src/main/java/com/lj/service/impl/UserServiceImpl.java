package com.lj.service.impl;

import com.lj.dao.UserMapper;
import com.lj.entity.User;
import com.lj.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User login(User user) {
        return userMapper.select(user);
    }

    @Override
    public User register(User user) {
        return userMapper.insertBy(user);
    }
}
