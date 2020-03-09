package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.Cart;

import java.util.List;


public interface CartService extends IService<Cart> {
    ResponseBase cartList( String customerId);

    ResponseBase addCart( Cart cart);

    ResponseBase delCart(Long customerId, Long productId);


}
