package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.service.db.SellerUserService;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sell")
public class SellController {

    @Resource
    SellerUserService sellerUserService;


    @RequestMapping("/listByKeyWord")
    public String getListByKeyWord(Model model , String fkeyword){
          List<SellerUserInfo> list = (List<SellerUserInfo>) sellerUserService.sellerList(fkeyword).getData();
        model.addAttribute("sellList",list);
        return "search_s" ;
    }

}
