package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.service.db.CartService;
import com.jiangxiacollege.canteenwebsite.customer.vo.CartVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CartController {


    @Resource
    private CartService cartService;


    @RequestMapping("/cart")
    public String cart(Model model, String customerId){
        List<CartVo> list = (List<CartVo>) cartService.cartList(customerId).getData();
        model.addAttribute("c",list);
        return "cart" ;
    }
}
