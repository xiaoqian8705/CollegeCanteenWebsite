package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.OrderMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.CartService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.OrderService;
import com.jiangxiacollege.canteenwebsite.customer.table.Order;
import com.jiangxiacollege.canteenwebsite.customer.table.OrderDetail;
import com.jiangxiacollege.canteenwebsite.customer.vo.CartVo;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderDetailVo;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderVo;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Resource
    public CartService cartService;

//订单列表
    @Override
    public ResponseBase orderInfo(String customerId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            List<OrderVo> orderList = this.baseMapper.orderInfo(customerId);
            responseBase.setData(orderList);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("订单查询错误");
        }
        return responseBase;
    }

    //订单详情
    @Override
    public ResponseBase orderDetail(String orderId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            List<OrderDetailVo> list = this.baseMapper.orderDetail(orderId);
            for(OrderDetailVo orderDetailVo : list){
                BigDecimal totalPrice = orderDetailVo.getPrice().multiply(new BigDecimal(orderDetailVo.getNumber()));
                orderDetailVo.setTotalPrice(totalPrice);
            }
            responseBase.setData(list);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("订单查询错误");
        }
        return responseBase;
    }

    public String buy(List<CartVo> cartVos, Integer addrId, HttpSession session){
        //1.生成订单表记录
      /*  Order order = new Order();
        order.setAddressId(addrId);
        User user = (User) session.getAttribute("user");
        order.setCustomerId(user.getId());
        order.setTime(new Time());
//        order.setOrderNum(UUID.randomUUID().toString());
        order.setOrderNum(OrderUtils.createOrderNum());//自定义生成订单编号
        order.setStatus("1");
        orderMapper.insert(order);
*/
        //2.生成订单明细表记录
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<Integer> cartIds = new ArrayList<>();
        for (CartVo cart: cartVos) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(cart.getProductId());
            orderDetail.setNumber(cart.getNumber());
//            orderDetail.setOrderId(order.getId());
            orderDetails.add(orderDetail);
//            cartIds.add(cart.getId());
        }
//      orderService.saveBatch(orderDetails);
        //3.删除购物车表中记录
        cartService.removeByIds(cartIds);
        return "success";
    }

}
