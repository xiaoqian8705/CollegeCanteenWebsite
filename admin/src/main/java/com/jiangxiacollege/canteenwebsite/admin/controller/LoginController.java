package com.jiangxiacollege.canteenwebsite.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    @RequestMapping("doLogin")
    public String doLogin(){
        return "login";
    }

}
