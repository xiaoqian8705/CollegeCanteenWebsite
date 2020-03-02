package com.jiangxiacollege.canteenwebsite.customer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class CartVo implements Serializable{

    private Long id;
    private  Long productId;
    private Long customerId;
    private Long sellerId;
    private int number;

    private String shopName;
    private  String photo;
    private  String name;
    private  BigDecimal price;
    private  BigDecimal totalPrice;

}
