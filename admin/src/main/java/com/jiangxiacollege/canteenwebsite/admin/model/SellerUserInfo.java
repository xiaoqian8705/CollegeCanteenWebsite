package com.jiangxiacollege.canteenwebsite.admin.model;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("seller_user_info")
public class SellerUserInfo implements Serializable {
    @TableId
    private Long id;
    private String user_name;
    private String password; //密码
    private String phone; //手机
    private String address;
    private String shop_name;//店名
    private String shop_type;//店铺类型
    private String school;
    private String photo;
    private int status;


}
