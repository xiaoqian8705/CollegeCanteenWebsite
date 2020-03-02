package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.service.db.OrderService;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import com.jiangxiacollege.canteenwebsite.customer.vo.CommentVo;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderVo;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/user_orderlist")
    public String orderList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String  userId =  session.getAttribute("userId").toString();
        List<OrderVo> orderList = (List) orderService.orderInfo(userId).getData();
        model.addAttribute("orderList", orderList);
        return "user_orderlist";
    }

    @RequestMapping("/user_order")
    public String order(Model model,String orderId){
       List<OrderVo> list = (List<OrderVo>) orderService.orderDetail(orderId).getData();
        model.addAttribute("orderDetail",list);
       return "user_order" ;
    }
}