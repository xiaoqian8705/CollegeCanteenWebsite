package com.jiangxiacollege.canteenwebsite.customer.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@TableName("address")
@Data
public class Address implements Serializable {
    @TableId(type= IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long customerId;

    private  String name;

    private String address;


    private String phone;



}
