package com.nd.spring_message.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController1 {

    @RequestMapping("/home")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);

        return "home";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login2";
    }




}
