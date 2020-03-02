package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.OrderMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.OrderService;
import com.jiangxiacollege.canteenwebsite.customer.table.Order;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderDetailVo;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


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



}
