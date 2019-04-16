package com.lj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafTestController {
    @RequestMapping("login")
    public String login(){
        return "login";
    }
}
