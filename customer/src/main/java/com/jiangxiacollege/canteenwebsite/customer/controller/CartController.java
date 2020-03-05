package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.service.db.CartService;
import com.jiangxiacollege.canteenwebsite.customer.vo.CartVo;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {


    @Resource
    private CartService cartService;


    @RequestMapping("/cart")
    public String cartList(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        String  userId =  session.getAttribute("userId").toString();
      List<CartVo>list = (List) cartService.cartList(userId).getData();
//        model.addAttribute("cartList",cartList);

        Map<String,List<CartVo>> map = new HashMap<>();
        for (CartVo cart: list ){
            if(map.get(cart.getShopName())==null){
                List<CartVo>  cartList = new ArrayList<>();
                cartList.add(cart);
                map.put(cart.getShopName(),cartList);
            }else {
                List<CartVo> cartList=  map.get(cart.getShopName());
                cartList.add(cart);
                map.put(cart.getShopName(),cartList);
            }
        }
        model.addAttribute("cartMap",map);
        return "cart" ;
    }




}
