package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.service.db.CartService;
import com.jiangxiacollege.canteenwebsite.customer.table.Cart;
import com.jiangxiacollege.canteenwebsite.customer.vo.CartVo;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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


    @RequestMapping("/addCart")
    public void addCart(HttpServletRequest request, HttpServletResponse response){
        String pro = request.getParameter("proId");
        Long proId =Long.parseLong(pro);
        String sell = request.getParameter("sellId");
        Long sellId =Long.parseLong(sell);
        int Number = Integer.parseInt(request.getParameter("Number"));
        Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("userId")));
        Cart cart = new Cart();
        cart.setCustomerId(userId);
        cart.setNumber(Number);
        cart.setProductId(proId);
        cart.setSellerId(sellId);
        cartService.addCart(cart);

        response.setContentType("text/html; charset=UTF-8"); //转码
        try {
            PrintWriter out = response.getWriter();
            out.write("<script>alert('加入购物车成功');</script>");
            response.sendRedirect("/cart");
            out.flush();
            out.close();
            out = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delCart")
    public ResponseBase delCart(HttpServletRequest request, String proId){
        String  userId = String.valueOf(request.getSession().getAttribute("userId"));
        Long customerId = Long.parseLong(userId);
        Long productId = Long.parseLong(proId);
        return  cartService.delCart(customerId,productId);

    }



}
