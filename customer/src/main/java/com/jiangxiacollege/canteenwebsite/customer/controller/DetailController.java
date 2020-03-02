package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.service.db.ProductService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.SellerUserService;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class DetailController {
    @Resource
    private SellerUserService sellerUserService;

    @Resource
    private ProductService productService;

    @RequestMapping("/shop")
    public  String detailList(Model model,String sellerId){
        SellerUserInfo sellerUserInfo = (SellerUserInfo) sellerUserService.sellerInfo(sellerId).getData();
        List<Product> sp =   (List)productService.productList(sellerId).getData();
        model.addAttribute("sellerUserInfo",sellerUserInfo);
        model.addAttribute("productList",sp);
        return "/shop";
    }
}
