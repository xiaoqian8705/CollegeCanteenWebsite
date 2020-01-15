package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.service.db.UserLoginService;
import com.jiangxiacollege.canteenwebsite.customer.table.CustomerUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/login")
@Controller
public class LoginController {


    @Resource
    private UserLoginService userLoginService;


    @RequestMapping(value = "/doRegister",method= RequestMethod.POST)
    @ResponseBody
    public ResponseBase doRegister(@RequestBody CustomerUserInfo customerUserInfo ){
      return userLoginService.doRegister(customerUserInfo);

    }

    @RequestMapping(value = "/doLogin",method= RequestMethod.POST)
    @ResponseBody
    public ResponseBase doLogin(@RequestBody CustomerUserInfo customerUserInfo, HttpServletRequest request){
        return userLoginService.doLogin(customerUserInfo,request);
    }

    @RequestMapping( "/login")
    public String login(){
        return "login";
    }
    @RequestMapping( "/register")
    public String register(){
        return "register";
    }

    @RequestMapping( "/cart")
    public String cart(){
        return "cart";
    }

}
