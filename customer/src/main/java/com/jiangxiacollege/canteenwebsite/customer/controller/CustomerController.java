package com.jiangxiacollege.canteenwebsite.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/customer")
@Controller
public class CustomerController {



    @RequestMapping( "/user_center")
    public String userCenter(){
        return "user_center";
    }

}
