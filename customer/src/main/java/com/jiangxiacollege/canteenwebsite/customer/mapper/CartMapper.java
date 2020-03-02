package com.jiangxiacollege.canteenwebsite.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxiacollege.canteenwebsite.customer.table.Cart;
import com.jiangxiacollege.canteenwebsite.customer.vo.CartVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CartMapper extends BaseMapper<Cart> {

        @Select("SELECT c.id,sui.shop_name,pro.photo,pro.name,c.number,pro.price FROM `cart` c LEFT JOIN `product` pro ON c.product_id = pro.id LEFT JOIN `seller_user_info` sui ON c.seller_id=sui.id where c.customer_id= #{customerId}")
        List<CartVo> cartList(String customerId);



        }
