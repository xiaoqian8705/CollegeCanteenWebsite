package com.jiangxiacollege.canteenwebsite.customer.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("comment")
@Data
public class Comment implements Serializable{
    @TableId(type= IdType.AUTO)
    private int id;
    private int productId;
    private int customerId;
    private int sellerId;
    private int orderId;
    private String writing;



}
