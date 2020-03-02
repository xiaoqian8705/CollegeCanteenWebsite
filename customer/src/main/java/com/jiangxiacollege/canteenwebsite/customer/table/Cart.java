package com.jiangxiacollege.canteenwebsite.customer.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("cart")
@Data
public class Cart implements Serializable {
    private Long id;
    private  Long productId;
    private Long customerId;
    private Long sellerId;
    private int number;

}
