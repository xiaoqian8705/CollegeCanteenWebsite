package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.CartMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.CartService;
import com.jiangxiacollege.canteenwebsite.customer.table.Cart;
import com.jiangxiacollege.canteenwebsite.customer.vo.CartVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {


    @Override
    public ResponseBase cartList(String customerId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            List<CartVo> list = this.baseMapper.cartList(customerId);
            /*for(CartVo cartVo : list){
                BigDecimal totalPrice = cartVo.getPrice().multiply(new BigDecimal(cartVo.getNumber()));
                cartVo.setTotalPrice(totalPrice);
            }*/
            responseBase.setData(list);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("订单查询错误");
        }
        return responseBase;
    }
}
