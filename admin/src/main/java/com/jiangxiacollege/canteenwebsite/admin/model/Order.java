package com.jiangxiacollege.canteenwebsite.admin.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
@TableName("order")
public class Order {

    @TableId
    private Long id;

    private Long sellerId;
    private Long customerId;
    private  Long productId;
    private  Long addressId;

    private int  status;
    private BigDecimal money;
    private String pay;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    private String edit;

}
