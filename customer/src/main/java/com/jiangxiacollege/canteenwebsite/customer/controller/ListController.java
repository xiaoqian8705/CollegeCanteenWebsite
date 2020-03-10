package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.service.db.SellerUserService;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ListController {

    @Resource
    private SellerUserService sellerUserService;

    @RequestMapping("/list")
    public  String sellerLists(Model model){
        List<SellerUserInfo> list =   (List)sellerUserService.sellerLists().getData();
        model.addAttribute("sellerLists",list);
        return "list";
    }
}
