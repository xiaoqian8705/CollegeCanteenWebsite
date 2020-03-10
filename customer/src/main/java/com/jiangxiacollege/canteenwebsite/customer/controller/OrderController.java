package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.service.db.AddressService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.OrderService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.ProductService;
import com.jiangxiacollege.canteenwebsite.customer.table.Address;

import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import com.jiangxiacollege.canteenwebsite.customer.utils.JsonUtils;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;



@Controller
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private ProductService productService;
    @Resource
    private AddressService addressService;

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

    @RequestMapping("/confirm_order")
    public  String address(Model model, HttpServletRequest request,String jsonPram){
        String[] sum = request.getParameterValues("sum");
        String[] checkProduct = request.getParameterValues("checkProduct");
        String[] allCarts = request.getParameterValues("sum1");
        List productIdList = new ArrayList();
        for(String checkCartId : checkProduct){
            for(String cart : allCarts){
                if(checkCartId.equals(cart.split(":")[0])){
                    productIdList.add(cart.split(",")[1]);
                }
            }
        }
        List<Product> listProduct = productService.getList(productIdList);
        List<OrderVo> orderVoList = new ArrayList<>();

        for(String cart2 : allCarts) {
            for (Product product : listProduct) {
                Long productId = Long.parseLong(cart2.split(",")[1]);
                if(productId.compareTo(product.getId())==0){
                    OrderVo orderVo = new OrderVo();
                    orderVo.setProductName(product.getName());
                    orderVo.setNumber(Integer.parseInt(cart2.split(":")[1].split(",")[0]));
                    orderVo.setPrice(product.getPrice());
                    orderVoList.add(orderVo);
                }
            }
        }

        HttpSession session = request.getSession();
        String  userId =  session.getAttribute("userId").toString();
        List<Address> addressList = (List) addressService.addressInfo(userId).getData();
        model.addAttribute("addressInfo", addressList);
        model.addAttribute("orderVoList", orderVoList);
        return "confirm_order";
    }

    public  String commitOrder(Integer addId,HttpSession session){

        return "";
    }
}