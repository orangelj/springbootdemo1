package com.lj.controller;

import com.lj.entity.User;
import com.lj.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("login")
    public String login(User user){
        User user1 = userService.login(user);
        if(user1!=null){
            return "forward:/employee/selectAll";
        }else {
            return "login.jsp";
        }
    }

    @RequestMapping("register")
    public String register(User user){
        User user1 = userService.register(user);
        if(user1!=null){
            return "forward:/employee/selectAll";
        }else {
            return "regist.jsp";
        }
    }
}
