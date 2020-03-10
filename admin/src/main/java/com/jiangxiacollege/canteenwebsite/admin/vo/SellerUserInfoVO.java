package com.jiangxiacollege.canteenwebsite.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class SellerUserInfoVO implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String user_name;
    private String password;
    private String phone;
    private String address;
    private String shop_name;
    private String shop_type;
    private String school;
    private String photo;
    private int status;

    private String userId;

}
