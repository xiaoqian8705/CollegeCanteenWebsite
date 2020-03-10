package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.service.db.CustomerUserInfoService;
import com.jiangxiacollege.canteenwebsite.customer.table.CustomerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.utils.MD5Tools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class CustomerController {

    @Resource
    private CustomerUserInfoService customerUserInfoService;

    @RequestMapping("/user_account")
    public  String detailList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String  userId =  session.getAttribute("userId").toString();
        CustomerUserInfo customerUserInfo = (CustomerUserInfo) customerUserInfoService.customerUserInfo(userId).getData();
        model.addAttribute("customerUserInfo", customerUserInfo);

        return "/user_account";
    }

    @RequestMapping("/updateCustomer")
    @ResponseBody
    public ResponseBase updateCustomer(HttpServletRequest request,@RequestBody CustomerUserInfo customerUserInfo ){
        HttpSession session = request.getSession();
        String  userId =  session.getAttribute("userId").toString();
        customerUserInfo.setId(Integer.parseInt(userId));
        String password = customerUserInfo.getPassword();
        String passwordMd5 = MD5Tools.getMD5(password);
        customerUserInfo.setPassword(passwordMd5);
        return customerUserInfoService.updateCustomer(customerUserInfo);
    }


}
