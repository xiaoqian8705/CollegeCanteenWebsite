package com.jiangxiacollege.canteenwebsite.customer.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentVo implements Serializable{
    private int id;
    private int productId;
    private int customerId;
    private int sellerId;
    private String writing;
    private String customer;



}
