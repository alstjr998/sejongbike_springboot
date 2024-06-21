package com.sejong.sejongbike.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/login")
    public String loginPage(){
        return "member/memberLogin";
    }
}
