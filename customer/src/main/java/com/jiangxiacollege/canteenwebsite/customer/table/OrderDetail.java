package com.jiangxiacollege.canteenwebsite.customer.table;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@TableName("order_detail")
@Data
public class OrderDetail implements Serializable {

    private Long id;
    private Long orderId;
    private  Long productId;
    private  int number;


}
