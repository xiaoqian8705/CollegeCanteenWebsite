package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.Order;


public interface OrderService extends IService<Order> {
    ResponseBase orderInfo(String customerId); //订单列表
    ResponseBase orderDetail(String orderId); //订单详情

}
