package com.jiangxiacollege.canteenwebsite.customer.table;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@TableName("address")
@Data
public class Address implements Serializable {

    private Long id;

    private Long customerId;

    private  String name;

    private String address;


    private String phone;



}
